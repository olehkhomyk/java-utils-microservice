package com.post_hub.utils_service.controller;

import com.post_hub.utils_service.model.constant.ApiLogMessage;
import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.request.ActionLogSearchRequest;
import com.post_hub.utils_service.model.response.PaginationResponse;
import com.post_hub.utils_service.model.response.UtilsResponse;
import com.post_hub.utils_service.service.ActionLogService;
import com.post_hub.utils_service.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("${end.point.logs}")
@RequiredArgsConstructor
public class ActionLogController {
    private final ActionLogService actionLogService;

    @GetMapping("${end.point.id}")
    public ResponseEntity<UtilsResponse<ActionLogDTO>> getById(
            @PathVariable(name = "id") Integer id,
            @RequestParam(name = "userId", required = false) Integer userId
    ) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

        UtilsResponse<ActionLogDTO> result = actionLogService.getById(id, userId);
        return ResponseEntity.ok(result);
    }

	@PostMapping("${end.point.search}")
	public ResponseEntity<UtilsResponse<PaginationResponse<ActionLogDTO>>> searchLogs(
			@RequestBody @Valid ActionLogSearchRequest request,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit
	) {
		log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

		Pageable pageable = PageRequest.of(page, limit);
		UtilsResponse<PaginationResponse<ActionLogDTO>> result = actionLogService.searchLogs(request, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping("${end.point.all}")
	public ResponseEntity<UtilsResponse<PaginationResponse<ActionLogDTO>>> searchLogs(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit
	) {
		log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

		Pageable pageable = PageRequest.of(page, limit);
		UtilsResponse<PaginationResponse<ActionLogDTO>> result = actionLogService.getAllLogs(pageable);
		return ResponseEntity.ok(result);
	}
}
