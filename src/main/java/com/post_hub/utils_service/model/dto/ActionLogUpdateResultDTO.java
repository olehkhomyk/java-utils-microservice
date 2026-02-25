package com.post_hub.utils_service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActionLogUpdateResultDTO {
	private int updatedCount;
	private List<Integer> updatedIds;
	private List<Integer> skippedIds;
}
