package com.post_hub.utils_service.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum ActionType {

    CREATE("Entity created"),
    UPDATE("Entity updated"),
    DELETE("Entity deleted"),
    LOGIN("User logged in");

    private final String description;
}
