package com.example.todolist.todo.service;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.domain.User;
import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoSaveOrUpdateResponse;
import com.example.todolist.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoCommandService {

    private final TodoRepository todoRepository;

    public TodoSaveOrUpdateResponse saveTodo(TodoSaveOrUpdateRequest todoSaveOrUpdateRequest) {
        Todo todo = Todo.createTodo(
                todoSaveOrUpdateRequest.getTodoName(),
                todoSaveOrUpdateRequest.getTodoCompleted(),
                checkTodoCompleted(todoSaveOrUpdateRequest.getTodoCompleted()),
                null
        );

        Todo savedTodo = todoRepository.save(todo);

        return TodoSaveOrUpdateResponse.of(savedTodo);
    }

    private LocalDateTime checkTodoCompleted(Boolean completed) {
        if (!completed) {
            return null;
        }

        return LocalDateTime.now();
    }
}
