package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    // Find notifications by message content (case insensitive)
    List<NotificationEntity> findByMessageContainingIgnoreCase(String message);
    
    // Find notifications by exact message
    List<NotificationEntity> findByMessage(String message);
}
