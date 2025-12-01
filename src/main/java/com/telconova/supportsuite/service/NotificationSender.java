package com.telconova.supportsuite.service;

import com.telconova.supportsuite.entity.Notification;

public interface NotificationSender {

    boolean send (Notification notification);

    // Valida si el canal puede enviar la notificaión

    boolean canSend(Notification notification);


}
