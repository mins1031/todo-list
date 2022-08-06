package com.example.todolist.todo.service;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.exception.NotFoundTodoException;
import com.example.todolist.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoCommandService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoDetailResponse saveTodo(TodoSaveOrUpdateRequest todoSaveOrUpdateRequest) {
        Todo todo = Todo.createTodo(
                todoSaveOrUpdateRequest.getTodoName(),
                todoSaveOrUpdateRequest.getTodoCompleted(),
                checkTodoCompleted(todoSaveOrUpdateRequest.getTodoCompleted(), null),
                null
        );

        Todo savedTodo = todoRepository.save(todo);

        return TodoDetailResponse.of(savedTodo);
    }

    @Transactional
    public TodoDetailResponse updateTodo(Integer todoId, TodoSaveOrUpdateRequest todoSaveOrUpdateRequest) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        todo.updateTodo(
                todoSaveOrUpdateRequest.getTodoName(),
                todoSaveOrUpdateRequest.getTodoCompleted(),
                checkTodoCompleted(todoSaveOrUpdateRequest.getTodoCompleted(), todo)
        );

        return TodoDetailResponse.of(todo);
    }

    @Transactional
    public void deleteTodo(Integer todoId) {
        todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        todoRepository.deleteById(todoId);
    }

    private LocalDateTime checkTodoCompleted(Boolean completed, Todo todo) {
        if (todo == null) {
            if (completed) {
                return LocalDateTime.now();
            }

            return null;
        }

        if (!todo.getCompleted() && !completed) {
            return null;
        }

        if (todo.getCompleted() && !completed) {
            return null;
        }

        if (todo.getCompleted() && completed) {
            return todo.getCompletedAt();
        }

        return LocalDateTime.now();
    }
}
