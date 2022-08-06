package com.example.todolist.todo.service;

import com.example.todolist.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoCommandService {

    private final TodoRepository todoRepository;
}
