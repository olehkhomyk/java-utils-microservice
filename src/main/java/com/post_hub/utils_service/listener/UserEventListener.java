package com.post_hub.utils_service.listener;

import com.post_hub.utils_service.model.dto.kafka.ActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {

    @KafkaListener(topics = "user-events", groupId = "utils-service-group")
    public void onUserEvent(ActionEvent event) {
        log.info("Received event: type={}, userId={}, email={}, at={}",
                event.getEventType(),
                event.getUserId(),
                event.getEmail(),
                event.getTimestamp());
    }
}
