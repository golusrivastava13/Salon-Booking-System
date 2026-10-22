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
        return List.of();
    }

    @Override
    public List<Notification> getAllNotificationBySalonId(Long salonId) {
        return List.of();
    }

    @Override
    public Notification markNotificationAsRead(Long notificationId) {
        return null;
    }
}
