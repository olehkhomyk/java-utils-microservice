package com.post_hub.utils_service.service;

import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.response.UtilsResponse;
import jakarta.validation.constraints.NotNull;

public interface ActionLogService {
    UtilsResponse<ActionLogDTO> getById(@NotNull Integer id, Integer userId);
}
