package com.post_hub.utils_service.repository;

import com.post_hub.utils_service.model.entity.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionLogRepository extends JpaRepository<ActionLog, Integer>, JpaSpecificationExecutor<ActionLog> {

    Optional<ActionLog> findByIdAndUserId(Integer id, Integer userId);

	/**
	 * Updates the isRead field to true for ActionLog entries with the specified IDs.
	 *
	 * @Modifying parameters:
	 * - clearAutomatically: When true, clears the persistence context after the query executes,
	 *   ensuring that subsequent queries fetch fresh data from the database rather than stale cached entities.
	 * - flushAutomatically: When true, flushes any pending changes to the database before executing
	 *   the query, ensuring the update operates on the most current state.
	 *
	 * @param ids List of ActionLog IDs to mark as read
	 * @return The number of rows updated
	 */
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query(value = "UPDATE ActionLog al SET al.isRead = true WHERE al.id IN :ids AND al.isRead = false")
	Integer setIsReadEqualsTrue(@Param("ids") List<Integer> ids);
}
