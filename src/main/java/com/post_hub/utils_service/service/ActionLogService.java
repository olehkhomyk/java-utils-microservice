package com.post_hub.utils_service.service;

import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.dto.ActionLogUpdateResultDTO;
import com.post_hub.utils_service.model.request.ActionLogIsReadRequest;
import com.post_hub.utils_service.model.request.ActionLogSearchRequest;
import com.post_hub.utils_service.model.response.PaginationResponse;
import com.post_hub.utils_service.model.response.UtilsResponse;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;

public interface ActionLogService {

    UtilsResponse<ActionLogDTO> getById(@NotNull Integer id, Integer userId);

	UtilsResponse<PaginationResponse<ActionLogDTO>> searchLogs(@NotNull ActionLogSearchRequest request, Pageable pageable);

	UtilsResponse<PaginationResponse<ActionLogDTO>> findAllLogs(@NotNull Pageable pageable);

	UtilsResponse<ActionLogUpdateResultDTO> setIsReadEqualsTrue(@NotNull ActionLogIsReadRequest request);
}
