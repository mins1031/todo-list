package com.example.todolist.todo.dto;

import com.example.todolist.common.dto.PageResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoSimpleResponses {
    private List<TodoSimpleResponse> todoSimpleResponses;
    private PageResponse pageResponse;
}
