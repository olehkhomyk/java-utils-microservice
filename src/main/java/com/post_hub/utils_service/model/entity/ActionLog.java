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
@Table(name = "action_logs")
@ToString
public class ActionLog {
	public static final String ID_FIELD_NAME = "id";
	public static final String USER_ID_FIELD_NAME = "userId";
	public static final String ACTION_TYPE_FIELD_NAME = "actionType";
	public static final String POST_HUB_SERVICE_FIELD_NAME = "postHubService";
	public static final String PRIORITY_TYPE_FIELD_NAME = "priorityType";
	public static final String IS_READ_FIELD_NAME = "isRead";
	public static final String MESSAGE_FIELD_NAME = "message";
	public static final String CREATED_AT_FIELD_NAME = "createdAt";

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