package com.post_hub.utils_service.repository.criteria;

import com.post_hub.utils_service.model.entity.ActionLog;
import com.post_hub.utils_service.model.request.ActionLogSearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ActionLogSearchCriteria implements Specification<ActionLog> {
	private final ActionLogSearchRequest request;

	@Override
	public Predicate toPredicate(
			Root<ActionLog> root,
			@Nullable CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();

		if (Objects.nonNull(request.getUserId())) {
			Predicate predicate = criteriaBuilder.equal(
					root.get(ActionLog.USER_ID_FIELD_NAME),
					request.getUserId()
			);
			predicates.add(predicate);
		}

		if (Objects.nonNull(request.getActionType())) {
			Predicate predicate = criteriaBuilder.equal(
					root.get(ActionLog.ACTION_TYPE_FIELD_NAME),
					request.getActionType()
			);
			predicates.add(predicate);
		}

		if (Objects.nonNull(request.getPriorityType())) {
			Predicate predicate = criteriaBuilder.equal(
					root.get(ActionLog.PRIORITY_TYPE_FIELD_NAME),
					request.getPriorityType()
			);
			predicates.add(predicate);
		}

		if (Objects.nonNull(request.getIsRead())) {
			Predicate predicate = criteriaBuilder.equal(
					root.get(ActionLog.IS_READ_FIELD_NAME),
					request.getIsRead()
			);
			predicates.add(predicate);
		}

		if (Objects.nonNull(request.getKeyword())) {
			String keyword = request.getKeyword().toLowerCase();

			Predicate predicate = criteriaBuilder.like(
					criteriaBuilder.lower(root.get(ActionLog.MESSAGE_FIELD_NAME)),
					"%" + keyword + "%"
			);
			predicates.add(predicate);
		}

		if (query != null) {
			sort(root, criteriaBuilder, query);
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	private void sort(Root<ActionLog> root, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query) {
		if (Objects.nonNull(request.getSortField())) {
			switch (request.getSortField()) {
				case ACTION_TYPE -> query.orderBy(criteriaBuilder.asc(root.get(ActionLog.ACTION_TYPE_FIELD_NAME)));
				case PRIORITY_TYPE -> query.orderBy(criteriaBuilder.asc(root.get(ActionLog.PRIORITY_TYPE_FIELD_NAME)));
				case CREATED_AT -> query.orderBy(criteriaBuilder.desc(root.get(ActionLog.CREATED_AT_FIELD_NAME)));
				case MESSAGE -> query.orderBy(criteriaBuilder.asc(root.get(ActionLog.MESSAGE_FIELD_NAME)));
				case IS_READ -> query.orderBy(criteriaBuilder.asc(root.get(ActionLog.IS_READ_FIELD_NAME)));
				default -> query.orderBy(criteriaBuilder.desc(root.get(ActionLog.ID_FIELD_NAME)));
			}
		} else {
			query.orderBy(criteriaBuilder.desc(root.get(ActionLog.ID_FIELD_NAME)));
		}
	}
}
