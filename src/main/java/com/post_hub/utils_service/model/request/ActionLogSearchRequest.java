package com.post_hub.utils_service.model.request;

import com.post_hub.utils_service.model.enums.ActionLogSortField;
import com.post_hub.utils_service.model.enums.ActionType;
import com.post_hub.utils_service.model.enums.PriorityType;
import lombok.Data;

@Data
public class ActionLogSearchRequest {
	private Integer userId;

	private ActionType actionType;
	private PriorityType priorityType;

	private Boolean isRead;
	private String keyword;
	private ActionLogSortField sortField;
}
