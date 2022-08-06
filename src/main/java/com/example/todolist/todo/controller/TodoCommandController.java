package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoSaveOrUpdateResponse;
import com.example.todolist.todo.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoCommandController {

    private final TodoCommandService todoCommandService;

    @PostMapping(TodoCommandControllerPath.TODO_SAVE_PATH)
    public ResponseEntity<TodoSaveOrUpdateResponse> saveTodo(
            @RequestBody TodoSaveOrUpdateRequest todoSaveOrUpdateRequest
    ) {
        TodoSaveOrUpdateResponse todoSaveOrUpdateResponse = todoCommandService.saveTodo(todoSaveOrUpdateRequest);
        return new ResponseEntity(todoSaveOrUpdateResponse, HttpStatus.CREATED);
    }

    @PutMapping(TodoCommandControllerPath.TODO_UPDATE_PATH)
    public ResponseEntity<TodoSaveOrUpdateResponse> updateTodo(
            @PathVariable Integer todoId,
            @RequestBody TodoSaveOrUpdateRequest todoSaveOrUpdateRequest
    ) {
        TodoSaveOrUpdateResponse todoSaveOrUpdateResponse = todoCommandService.updateTodo(todoId, todoSaveOrUpdateRequest);
        return new ResponseEntity(todoSaveOrUpdateResponse, HttpStatus.CREATED);
    }
}
