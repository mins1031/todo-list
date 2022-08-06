package com.example.todolist.todo.controller;

import com.example.todolist.todo.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

public class TodoCommandControllerPath {
    public static final String TODO_SAVE_PATH = "/todos";
    public static final String TODO_UPDATE_PATH = "/todos/{todoId}";
    public static final String TODO_DELETE_PATH = "/todos/{todoId}";
}
