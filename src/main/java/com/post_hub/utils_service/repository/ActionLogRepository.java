package com.post_hub.utils_service.repository;

import com.post_hub.utils_service.model.entity.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Integer> {
}
