package com.telconova.supportsuite.repository;

import com.telconova.supportsuite.entity.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    // CORRECCIÓN: Cambiar findByNotification a findByNotificationId
    List<NotificationHistory> findByNotificationIdOrderByTimestampDesc(Long notificationId);

}