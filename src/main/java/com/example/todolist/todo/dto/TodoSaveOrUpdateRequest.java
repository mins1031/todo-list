package com.example.todolist.todo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoSaveOrUpdateRequest {
    private String todoName;
    private Boolean todoCompleted;
}
