package com.post_hub.utils_service.mapper;

import com.post_hub.utils_service.model.dto.ActionLogDTO;
import com.post_hub.utils_service.model.entity.ActionLog;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ActionLogMapper {
    ActionLogDTO toDTO(ActionLog actionLog);
    ActionLog toEntity(ActionLogDTO dto);
}
