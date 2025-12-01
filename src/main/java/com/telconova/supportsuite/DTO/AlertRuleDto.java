package com.telconova.supportsuite.DTO;

import com.telconova.supportsuite.entity.EventTrigger;
import com.telconova.supportsuite.entity.NotificationChannel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertRuleDto {
    private Long id;
    private String name;
    private String description;
    private EventTrigger eventTrigger;
    private Long templateId;
    private String templateName;
    private String targetAudience;
    private NotificationChannel channel;
    private Boolean isActive;
    private Integer priority;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
