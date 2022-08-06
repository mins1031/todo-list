package com.example.todolist.todo.service;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoSaveOrUpdateResponse;
import com.example.todolist.todo.repository.TodoRepository;
import com.example.todolist.todo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoCommandServiceTest {

    @Autowired
    private TodoCommandService todoCommandService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("투두를 생성할 수 있다.")
    @Test
    public void 실행예정인_투두_생성_테스트() {
        //given
        TodoSaveOrUpdateRequest todoSaveOrUpdateRequest = new TodoSaveOrUpdateRequest("name", false);

        //when
        TodoSaveOrUpdateResponse todoSaveOrUpdateResponse = todoCommandService.saveTodo(todoSaveOrUpdateRequest);

        //then
        Assertions.assertThat(todoSaveOrUpdateResponse.getId()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getName()).isEqualTo(todoSaveOrUpdateRequest.getTodoName());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompleted()).isEqualTo(todoSaveOrUpdateRequest.getTodoCompleted());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompletedAt()).isNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getCreatedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getUpdatedAt()).isNotNull();

    }
}