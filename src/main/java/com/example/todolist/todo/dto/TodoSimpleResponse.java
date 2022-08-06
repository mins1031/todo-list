package com.example.todolist.todo.dto;

import com.example.todolist.todo.domain.Todo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TodoSimpleResponse {
    private Integer id;
    private String name;
    private Boolean completed;

    public static TodoSimpleResponse of(Todo todo) {
        return new TodoSimpleResponse(todo.getId(), todo.getName(), todo.getCompleted());
    }

}
