package org.example.expert.domain.comment.repository;

import org.example.expert.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 7번 핵심: 유저(user) 정보를 한 번에 가져와서 N+1 문제를 방지합니다.
    @EntityGraph(attributePaths = {"user"})
    List<Comment> findAllByTodoId(Long todoId);
}