package com.example.todolist.todo.exception;

public class NotFoundTodoException extends RuntimeException {
    private static final String MESSAGE = "요청한 번호의 to-do 항목이 없습니다. 잘못된 요청입니다.";

    public NotFoundTodoException() {
        super(MESSAGE);
    }
}
