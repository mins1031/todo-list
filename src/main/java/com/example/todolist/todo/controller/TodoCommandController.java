package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<TodoDetailResponse> saveTodo(
            @RequestBody TodoSaveOrUpdateRequest todoSaveOrUpdateRequest
    ) {
        TodoDetailResponse todoDetailResponse = todoCommandService.saveTodo(todoSaveOrUpdateRequest);
        return new ResponseEntity(todoDetailResponse, HttpStatus.CREATED);
    }

    @PutMapping(TodoCommandControllerPath.TODO_UPDATE_PATH)
    public ResponseEntity<TodoDetailResponse> updateTodo(
            @PathVariable Integer todoId,
            @RequestBody TodoSaveOrUpdateRequest todoSaveOrUpdateRequest
    ) {
        TodoDetailResponse todoDetailResponse = todoCommandService.updateTodo(todoId, todoSaveOrUpdateRequest);
        return new ResponseEntity(todoDetailResponse, HttpStatus.OK);
    }

    @DeleteMapping(TodoCommandControllerPath.TODO_DELETE_PATH)
    public ResponseEntity<TodoDetailResponse> deleteTodo(@PathVariable Integer todoId) {
        todoCommandService.deleteTodo(todoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
