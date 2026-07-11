package com.post_hub.utils_service.model.dto.kafka;

import com.post_hub.utils_service.model.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionEvent {
    private EventType eventType;
    private LocalDateTime timestamp;
    private Integer userId;
    private String email;
    private String service;
    private String message;
    private Map<String, Object> details;
}
