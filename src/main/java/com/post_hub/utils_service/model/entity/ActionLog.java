package com.post_hub.utils_service.model.entity;

import com.post_hub.utils_service.model.enums.ActionType;
import com.post_hub.utils_service.model.enums.PostHubService;
import com.post_hub.utils_service.model.enums.PriorityType;
import com.post_hub.utils_service.utils.enum_converter.PostHubServiceConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "action_logs", schema = "v1_utils_service")
@ToString
public class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "action_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ActionType actionType;

    @Column(name = "priority_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PriorityType priorityType;

    @ColumnDefault("false")
    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

    @Column(name = "message", nullable = false, updatable = false)
    private String message;

    @Column(name = "service", nullable = false)
    @Convert(converter = PostHubServiceConverter.class)
    private PostHubService service;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}