package com.post_hub.utils_service.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActionLogIsReadRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private List<Integer> ids;
}
