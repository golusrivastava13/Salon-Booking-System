package com.salonbooking.service;

import com.salonbooking.domain.BookingStatus;
import com.salonbooking.dto.BookingRequest;
import com.salonbooking.dto.SalonDTO;
import com.salonbooking.dto.ServiceDTO;
import com.salonbooking.dto.UserDTO;
import com.salonbooking.modal.Booking;
import com.salonbooking.modal.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon,
                          Set<ServiceDTO> serviceDTOset) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingBySalon(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);



}
