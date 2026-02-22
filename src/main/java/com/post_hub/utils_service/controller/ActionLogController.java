package com.post_hub.utils_service.controller;

import com.post_hub.utils_service.model.constant.ApiLogMessage;
import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.response.UtilsResponse;
import com.post_hub.utils_service.service.ActionLogService;
import com.post_hub.utils_service.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("${end.point.logs}")
@RequiredArgsConstructor
public class ActionLogController {
    private final ActionLogService actionLogService;

    @GetMapping("${end.point.id}")
    public ResponseEntity<UtilsResponse<ActionLogDTO>> getById(
            @PathVariable(name = "id") Integer id
    ) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

        UtilsResponse<ActionLogDTO> result = actionLogService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("${end.point.id}/{userId}")
    public ResponseEntity<UtilsResponse<ActionLogDTO>> getByIdAndUserId(
            @PathVariable(name = "id") Integer logId,
            @PathVariable(name = "userId") Integer userId
    ) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodName());

        UtilsResponse<ActionLogDTO> result = actionLogService.getByIdAndUserId(logId, userId);
        return ResponseEntity.ok(result);
    }
}
