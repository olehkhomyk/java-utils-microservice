package com.post_hub.utils_service.service.impl;

import com.post_hub.utils_service.mapper.ActionLogMapper;
import com.post_hub.utils_service.model.constant.ApiErrorMessage;
import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.entity.ActionLog;
import com.post_hub.utils_service.model.exception.NotFoundException;
import com.post_hub.utils_service.model.request.ActionLogSearchRequest;
import com.post_hub.utils_service.model.response.PaginationResponse;
import com.post_hub.utils_service.model.response.UtilsResponse;
import com.post_hub.utils_service.repository.ActionLogRepository;
import com.post_hub.utils_service.repository.criteria.ActionLogSearchCriteria;
import com.post_hub.utils_service.service.ActionLogService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionLogServiceImpl implements ActionLogService {
    private final ActionLogRepository actionLogRepository;
    private final ActionLogMapper actionLogMapper;

    @Override
    public UtilsResponse<ActionLogDTO> getById(@NotNull Integer id, Integer userId) {
        ActionLog actionLog;

        if (userId != null) {
            actionLog = actionLogRepository.findByIdAndUserId(id, userId)
                    .orElseThrow(() -> new NotFoundException(ApiErrorMessage.NOT_FOUND_LOG_FOR_USER.getMessage(id, userId)));
        } else {
            actionLog = actionLogRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ApiErrorMessage.NOT_FOUND_LOG_ID.getMessage(id)));
        }

        ActionLogDTO actionLogDTO = actionLogMapper.toDTO(actionLog);

        return UtilsResponse.createSuccessful(actionLogDTO);
    }

	@Override
	public UtilsResponse<PaginationResponse<ActionLogDTO>> searchLogs(@NotNull ActionLogSearchRequest request, Pageable pageable) {
		Specification<ActionLog> specification = new ActionLogSearchCriteria(request);
		Page<ActionLogDTO> actionLogs  = actionLogRepository.findAll(specification, pageable)
				.map(actionLogMapper::toDTO);

		PaginationResponse<ActionLogDTO> response = PaginationResponse.<ActionLogDTO>builder()
				.content(actionLogs.getContent())
				.pagination(
						PaginationResponse.Pagination.builder()
								.total(actionLogs.getTotalElements())
								.limit(pageable.getPageSize())
								.page(actionLogs.getNumber() + 1)
								.pages(actionLogs.getTotalPages())
								.build()
				).build();

		return UtilsResponse.createSuccessful(response);
	}
}
