package com.telconova.supportsuite.controller;


import com.telconova.supportsuite.DTO.CreateNotificationRequest;
import com.telconova.supportsuite.DTO.NotificationDTO;
import com.telconova.supportsuite.DTO.NotificationStatusDTO;
import com.telconova.supportsuite.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "https://telco-nova-p7-f4-front.vercel.app")
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor // Genera el constructor para el campo 'final'
public class NotificationsController {

    private final NotificationService notificationService;

    // POST /api/v1/notifications
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {
        NotificationDTO notification = notificationService.createNotification(request);
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }



    // GET /api/v1/notifications/stats
    @GetMapping("/stats")
    public ResponseEntity<NotificationStatusDTO> getEstadisticas(){
        NotificationStatusDTO stast = notificationService.getEstadisticas();
        return ResponseEntity.ok(stast);
    }

    @GetMapping("/errors")
    public ResponseEntity<List<NotificationDTO>> getErrorLogs() {
        // Usamos NotificationDTO porque contiene el mensaje de error y el conteo de reintentos
        List<NotificationDTO> errorLogs = notificationService.getErrorLogs();
        return ResponseEntity.ok(errorLogs);
    }

    @GetMapping("/queue")
    public ResponseEntity<List<NotificationDTO>> getPendingQueue() {
        List<NotificationDTO> pendingNotifications = notificationService.getPendingQueueNotifications();
        return ResponseEntity.ok(pendingNotifications);
    }

}
