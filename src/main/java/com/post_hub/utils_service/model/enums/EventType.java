package com.post_hub.utils_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum EventType {

    USER_REGISTERED("User registered in the system"),
    USER_LOGGED_IN("User logged in");

    private final String description;
}
