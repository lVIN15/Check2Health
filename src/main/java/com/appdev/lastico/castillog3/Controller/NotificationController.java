package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.NotificationEntity;
import com.appdev.lastico.castillog3.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get all notifications
    @GetMapping
    public ResponseEntity<List<NotificationEntity>> getAllNotifications() {
        List<NotificationEntity> notifications = notificationRepository.findAll();
        return ResponseEntity.ok(notifications);
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable Long id) {
        Optional<NotificationEntity> notification = notificationRepository.findById(id);
        return notification.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // Create new notification
    @PostMapping
    public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notification) {
        NotificationEntity createdNotification = notificationRepository.save(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    // Update notification
    @PutMapping("/{id}")
    public ResponseEntity<NotificationEntity> updateNotification(@PathVariable Long id, @RequestBody NotificationEntity notificationDetails) {
        Optional<NotificationEntity> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            NotificationEntity notification = optionalNotification.get();
            notification.setMessage(notificationDetails.getMessage());
            
            NotificationEntity updatedNotification = notificationRepository.save(notification);
            return ResponseEntity.ok(updatedNotification);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete all notifications
    @DeleteMapping
    public ResponseEntity<Void> deleteAllNotifications() {
        notificationRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
