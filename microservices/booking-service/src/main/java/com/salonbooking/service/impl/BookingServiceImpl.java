package com.salonbooking.service.impl;

import com.salonbooking.domain.BookingStatus;
import com.salonbooking.dto.BookingRequest;
import com.salonbooking.dto.SalonDTO;
import com.salonbooking.dto.ServiceDTO;
import com.salonbooking.dto.UserDTO;
import com.salonbooking.modal.Booking;
import com.salonbooking.modal.SalonReport;
import com.salonbooking.repository.BookingRepository;
import com.salonbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    
    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, 
                                 SalonDTO salon, 
                                 Set<ServiceDTO> serviceDTOset) throws Exception {
        int totalDuration = serviceDTOset.stream()
                .mapToInt(ServiceDTO::getDuration).sum();

        LocalDateTime bookingStartTime=booking.getStartTime();
        LocalDateTime bookingEndTime=bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable=isTimeSlotAvailable(salon, bookingStartTime, bookingEndTime);

        int totalPrice=serviceDTOset.stream()
                .mapToInt(ServiceDTO::getPrice).sum();

        Set<Long> idList=serviceDTOset.stream()
                .map(ServiceDTO::getId)
                .collect(Collectors.toSet());

        Booking newBooking=new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
                
        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(
            SalonDTO salonDTO,
            LocalDateTime bookingStartTime,
            LocalDateTime bookingEndTime
    ) throws Exception {

        // 1. Validate booking time
        if (bookingStartTime == null || bookingEndTime == null) {
            throw new Exception(
                    "Booking start time and end time are required"
            );
        }

        // 2. Validate booking duration
        if (!bookingEndTime.isAfter(bookingStartTime)) {
            throw new Exception(
                    "Booking end time must be after start time"
            );
        }

        // 3. Validate salon working hours
        if (salonDTO.getOpenTime() == null
                || salonDTO.getCloseTime() == null) {

            throw new Exception(
                    "Salon working hours are not configured"
            );
        }

        // 4. Get salon working hours for booking date
        LocalDateTime salonOpenTime =
                salonDTO.getOpenTime()
                        .atDate(bookingStartTime.toLocalDate());

        LocalDateTime salonCloseTime =
                salonDTO.getCloseTime()
                        .atDate(bookingStartTime.toLocalDate());

        // 5. Check booking within working hours
        if (bookingStartTime.isBefore(salonOpenTime)
                || bookingEndTime.isAfter(salonCloseTime)) {

            throw new Exception(
                    "Booking time must be within salon's working hours"
            );
        }

        // 6. Get existing bookings
        List<Booking> existingBookings =
                getBookingBySalon(salonDTO.getId());

        // 7. Check slot overlap
        for (Booking existingBooking : existingBookings) {

            LocalDateTime existingStart =
                    existingBooking.getStartTime();

            LocalDateTime existingEnd =
                    existingBooking.getEndTime();

            if (bookingStartTime.isBefore(existingEnd)
                    && bookingEndTime.isAfter(existingStart)) {

                throw new Exception(
                        "Slot not available, choose a different time."
                );
            }
        }

        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking=bookingRepository.findById(id).orElse(null);
        if(booking==null){
            throw new Exception("booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking=getBookingById(bookingId);
        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings=getBookingBySalon(salonId);

        if(date==null){
            return allBookings;
        }

        return allBookings.stream()
                .filter(booking -> isSameDate(booking.getStartTime(),date) ||
                        isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());

    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings=getBookingBySalon(salonId);
        int totalEarnings=bookings.stream()
                .mapToInt(Booking::getTotalPrice).sum();

        Integer totalBooking=bookings.size();

        List<Booking> cancelledBookings=bookings.stream()
                .filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefund=cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice).sum();

        SalonReport report=new SalonReport();
        report.setSalonId(salonId);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalBookings(totalEarnings);
        report.setTotalEarnings(totalEarnings);
        report.setTotalRefund(totalRefund);
        report.setTotalBookings(totalBooking);

        return report;
    }
}
