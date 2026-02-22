package com.post_hub.utils_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PostHubService {
    IAM_SERVICE("iam-service"),
    UNDEFINED_SERVICE("Undefined-service"),
    ;

    private final String value;

    public static PostHubService fromValue(String value) {
        return Arrays.stream(PostHubService.values())
                .filter(service -> service.getValue().equals(value))
                .findFirst()
                .orElse(UNDEFINED_SERVICE);
    }
}

