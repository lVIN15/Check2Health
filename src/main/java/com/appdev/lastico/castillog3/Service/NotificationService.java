package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.NotificationEntity;
import com.appdev.lastico.castillog3.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get all notifications
    public List<NotificationEntity> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Optional<NotificationEntity> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Create new notification
    public NotificationEntity createNotification(NotificationEntity notification) {
        return notificationRepository.save(notification);
    }

    // Update existing notification
    public NotificationEntity updateNotification(Long id, NotificationEntity notificationDetails) {
        Optional<NotificationEntity> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            NotificationEntity notification = optionalNotification.get();
            notification.setMessage(notificationDetails.getMessage());
            return notificationRepository.save(notification);
        }
        return null;
    }

    // Delete notification
    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Delete all notifications
    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }
}
