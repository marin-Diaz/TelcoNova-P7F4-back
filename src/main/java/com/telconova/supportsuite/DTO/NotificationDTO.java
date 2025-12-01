package com.telconova.supportsuite.DTO;

import com.telconova.supportsuite.entity.NotificationChannel;

import com.telconova.supportsuite.entity.Notification.NotificationStatus;
import lombok.Data;
// Importamos la Entidad Notification para el Enum.

import java.time.LocalDateTime;

@Data
public class NotificationDTO {

    private Long id;
    private String recipient;
    private String subject;
    private String content;

    private NotificationChannel channel;
    private NotificationStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
    private Integer reintentosCount;
    private String errorMenssage;

}