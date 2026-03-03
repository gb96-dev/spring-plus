package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import java.util.Optional;

public interface TodoRepositoryCustom {
    // 8번 핵심: JPQL 대신 QueryDSL로 구현할 메서드
    Optional<Todo> findByIdWithUser(Long todoId);
}