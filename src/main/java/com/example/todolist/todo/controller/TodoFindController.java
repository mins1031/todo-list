package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.dto.TodoSimpleResponses;
import com.example.todolist.todo.service.TodoFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoFindController {

    private final TodoFindService todoFindService;

    @GetMapping(TodoFindControllerPath.TODO_FIND)
    public ResponseEntity<TodoDetailResponse> findTodo(@PathVariable Integer todoId) {
        TodoDetailResponse todoDetailResponse = todoFindService.findTodo(todoId);
        return new ResponseEntity(todoDetailResponse, HttpStatus.OK);
    }

    @GetMapping(TodoFindControllerPath.TODO_FINDS)
    public ResponseEntity<TodoDetailResponse> findTodos(
            @RequestParam("skip") String skip,
            @PageableDefault(sort = "updatedDate", direction = Sort.Direction.DESC, size = 100) Pageable pageable
    ) {
        TodoSimpleResponses todoSimpleResponses = todoFindService.findTodos(pageable, skip);
        return new ResponseEntity(todoSimpleResponses, HttpStatus.OK);
    }
}
