package com.post_hub.utils_service.service.impl;

import com.post_hub.utils_service.mapper.ActionLogMapper;
import com.post_hub.utils_service.model.constant.ApiErrorMessage;
import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.entity.ActionLog;
import com.post_hub.utils_service.model.exception.NotFoundException;
import com.post_hub.utils_service.model.response.UtilsResponse;
import com.post_hub.utils_service.repository.ActionLogRepository;
import com.post_hub.utils_service.service.ActionLogService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionLogServiceImpl implements ActionLogService {
    private final ActionLogRepository actionLogRepository;
    private final ActionLogMapper actionLogMapper;

    @Override
    public UtilsResponse<ActionLogDTO> getById(@NotNull Integer id) {
        ActionLog actionLog = actionLogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.NOT_FOUND_LOG_ID.getMessage(id)));

        ActionLogDTO actionLogDTO = actionLogMapper.toDTO(actionLog);

        return UtilsResponse.createSuccessful(actionLogDTO);
    }

    @Override
    public UtilsResponse<ActionLogDTO> getByIdAndUserId(@NotNull Integer id, @NotNull Integer userId) {
        ActionLog actionLog = actionLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.NOT_FOUND_LOG_FOR_USER.getMessage(id, userId)));

        ActionLogDTO actionLogDTO = actionLogMapper.toDTO(actionLog);

        return UtilsResponse.createSuccessful(actionLogDTO);
    }
}
