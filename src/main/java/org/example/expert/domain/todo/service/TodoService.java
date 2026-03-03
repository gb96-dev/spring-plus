package org.example.expert.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final WeatherClient weatherClient; // 날씨 조회를 위해 필요

    @Transactional // 1번 과제에서 했던 Transactional 추가!
    public TodoSaveResponse saveTodo(AuthUser authUser, TodoSaveRequest todoSaveRequest) {
        User user = User.fromAuthUser(authUser);
        String weather = weatherClient.getTodayWeather();

        // 6번 핵심: Todo를 생성할 때 Todo 엔티티 생성자에서 Manager를 리스트에 담습니다.
        Todo todo = new Todo(
                todoSaveRequest.getTitle(),
                todoSaveRequest.getContents(),
                weather,
                user
        );
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponse(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContents(),
                weather,
                new UserResponse(user.getId(), user.getEmail())
        );
    }

    public Page<TodoResponse> getTodos(int page, int size, String weather, String startDate, String endDate) {
        Pageable pageable = PageRequest.of(page - 1, size);
        LocalDateTime startDateTime = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate).atStartOfDay() : null;
        LocalDateTime endDateTime = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate).atTime(LocalTime.MAX) : null;

        Page<Todo> todos = todoRepository.findAllByConditions(weather, startDateTime, endDateTime, pageable);

        return todos.map(todo -> new TodoResponse(
                todo.getId(), todo.getTitle(), todo.getContents(), todo.getWeather(),
                new UserResponse(todo.getUser().getId(), todo.getUser().getEmail()),
                todo.getCreatedAt(), todo.getModifiedAt()
        ));
    }

    public TodoResponse getTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new InvalidRequestException("Todo not found"));

        return new TodoResponse(
                todo.getId(), todo.getTitle(), todo.getContents(), todo.getWeather(),
                new UserResponse(todo.getUser().getId(), todo.getUser().getEmail()),
                todo.getCreatedAt(), todo.getModifiedAt()
        );
    }
}