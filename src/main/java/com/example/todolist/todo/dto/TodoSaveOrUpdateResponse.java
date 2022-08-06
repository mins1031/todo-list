package com.example.todolist.todo.dto;

import com.example.todolist.todo.domain.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoSaveOrUpdateResponse {
    private Integer id;
    private String name;
    private Boolean completed;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TodoSaveOrUpdateResponse of(Todo savedTodo) {
        return new TodoSaveOrUpdateResponse(
                savedTodo.getId(),
                savedTodo.getName(),
                savedTodo.getCompleted(),
                savedTodo.getCompletedAt(),
                savedTodo.getCreatedAt(),
                savedTodo.getUpdatedAt()
        );
    }
}
