package com.post_hub.utils_service.service.impl;

import com.post_hub.utils_service.mapper.ActionLogMapper;
import com.post_hub.utils_service.model.constant.ApiErrorMessage;
import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.dto.ActionLogUpdateResultDTO;
import com.post_hub.utils_service.model.entity.ActionLog;
import com.post_hub.utils_service.model.exception.NotFoundException;
import com.post_hub.utils_service.model.request.ActionLogIsReadRequest;
import com.post_hub.utils_service.model.request.ActionLogSearchRequest;
import com.post_hub.utils_service.model.response.PaginationResponse;
import com.post_hub.utils_service.model.response.UtilsResponse;
import com.post_hub.utils_service.repository.ActionLogRepository;
import com.post_hub.utils_service.repository.criteria.ActionLogSearchCriteria;
import com.post_hub.utils_service.service.ActionLogService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		Page<ActionLogDTO> actionLogs = actionLogRepository.findAll(specification, pageable)
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

	@Override
	public UtilsResponse<PaginationResponse<ActionLogDTO>> findAllLogs(@NotNull Pageable pageable) {
		Page<ActionLogDTO> actionLogs = actionLogRepository.findAll(pageable)
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

	@Override
	@Transactional()
	public UtilsResponse<ActionLogUpdateResultDTO> setIsReadEqualsTrue(@NotNull ActionLogIsReadRequest request) {
		Integer userId = request.getUserId();
		List<ActionLog> logs = actionLogRepository.findAllById(request.getIds());

		Map<Boolean, List<Integer>> partitioned = logs.stream()
				.collect(
						Collectors.partitioningBy(
								log -> log.getUserId().equals(userId),
								Collectors.mapping(ActionLog::getId, Collectors.toList())
						)
				);

		List<Integer> allowedIds = partitioned.get(true);
		List<Integer> skippedIds = partitioned.get(true);

		int updatedCount = allowedIds.isEmpty() ? 0 : actionLogRepository.setIsReadEqualsTrue(allowedIds);

		return UtilsResponse.createSuccessful(
				ActionLogUpdateResultDTO.builder()
						.updatedCount(updatedCount)
						.updatedIds(allowedIds)
						.skippedIds(skippedIds)
						.build()
		);
	}
}
