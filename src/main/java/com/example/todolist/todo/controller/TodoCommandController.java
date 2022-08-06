package com.example.todolist.todo.controller;

import com.example.todolist.todo.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoCommandController {

    private final TodoCommandService todoCommandService;
}
