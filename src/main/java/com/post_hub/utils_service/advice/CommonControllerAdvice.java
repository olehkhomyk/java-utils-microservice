package com.post_hub.utils_service.advice;

import com.post_hub.utils_service.model.constant.ApiConstants;
import com.post_hub.utils_service.model.exception.NotFoundException;
import com.post_hub.utils_service.model.response.UtilsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;
import java.time.zone.ZoneRulesException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {
	@ExceptionHandler
	@ResponseBody
	protected ResponseEntity<UtilsResponse<String>> handleException(Exception e) {
		logStackTrace(e);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(UtilsResponse.createFailed(e.getMessage()));
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	protected ResponseEntity<UtilsResponse<String>> handleNotFoundException(NotFoundException e) {
		logStackTrace(e);

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(UtilsResponse.createFailed(e.getMessage()));
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	protected ResponseEntity<UtilsResponse<String>> handleAccessDeniedException(AccessDeniedException e) {
		logStackTrace(e);

		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(UtilsResponse.createFailed(e.getMessage()));
	}

	@ExceptionHandler(ZoneRulesException.class)
	@ResponseBody
	protected  ResponseEntity<UtilsResponse<String>> handleZoneRulesException(ZoneRulesException e) {
		logStackTrace(e);

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(UtilsResponse.createFailed(e.getMessage()));
	}

	private void logStackTrace(Exception ex) {
		StringBuilder stackTrace = new StringBuilder();

		stackTrace.append(ApiConstants.ANSI_RED);

		stackTrace.append(ex.getMessage()).append(ApiConstants.BREAK_LINE);

		if (Objects.nonNull(ex.getCause())) {
			stackTrace.append(ex.getCause().getMessage()).append(ApiConstants.BREAK_LINE);
		}

		Arrays.stream(ex.getStackTrace())
				.filter(st -> st.getClassName().startsWith(ApiConstants.TIME_ZONE_PACKAGE_NAME))
				.forEach(st -> stackTrace
						.append(st.getClassName())
						.append(".")
						.append(st.getMethodName())
						.append(" (")
						.append(st.getLineNumber())
						.append(") ")
				);

		log.error(stackTrace.append(ApiConstants.ANSI_WHITE).toString());
	}
}
