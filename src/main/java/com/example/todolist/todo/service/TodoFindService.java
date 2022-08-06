package com.example.todolist.todo.service;

import com.example.todolist.common.dto.PageResponse;
import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.dto.TodoSimpleResponse;
import com.example.todolist.todo.dto.TodoSimpleResponses;
import com.example.todolist.todo.exception.NotFoundTodoException;
import com.example.todolist.todo.repository.TodoRepository;
import com.example.todolist.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoFindService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public TodoDetailResponse findTodo(Integer todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(NotFoundTodoException::new);
        return TodoDetailResponse.of(todo);
    }

    @Transactional(readOnly = true)
    public TodoSimpleResponses findTodos(Pageable pageable, String skip) {
        Page<Todo> todos = todoRepository.findAll(pageable);
        List<TodoSimpleResponse> todoSimpleResponses = todos.getContent().stream()
                .map(TodoSimpleResponse::of)
                .collect(Collectors.toList());
        return new TodoSimpleResponses(todoSimpleResponses, PageResponse.of(todos.getTotalPages(), pageable.getPageNumber()));
    }
}
