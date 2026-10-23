package com.salonbooking.service.impl;

import com.salonbooking.mapper.NotificationMapper;
import com.salonbooking.modal.Notification;
import com.salonbooking.payload.dto.BookingDTO;
import com.salonbooking.payload.dto.NotificationDTO;
import com.salonbooking.repository.NotificationRepository;
import com.salonbooking.service.NotificationService;
import com.salonbooking.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;

    @Override
    public NotificationDTO createNotification(Notification notification) throws Exception {

        Notification savedNotification = notificationRepository.save(notification);
        BookingDTO bookingDTO = bookingFeignClient.getBookingById(
                savedNotification.getBookingId()
        ).getBody();
        NotificationDTO notificationDTO = NotificationMapper.toDTO(savedNotification,bookingDTO);
        return notificationDTO;
    }

    @Override
    public List<Notification> getAllNotificationByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotificationBySalonId(Long salonId) {
        return notificationRepository.findBySalonId(salonId);
    }

    @Override
    public Notification markNotificationAsRead(Long notificationId) throws Exception {
        return notificationRepository.findById(notificationId).map(
                notification -> {
                    notification.setRead(true);
                    return notificationRepository.save(notification);
                }
        ).orElseThrow(()-> new Exception("Notification not found"));
    }
}
