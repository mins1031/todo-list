package com.example.todolist.todo.service;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoSaveOrUpdateRequest;
import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.repository.TodoRepository;
import com.example.todolist.todo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TodoCommandServiceTest {

    @Autowired
    private TodoCommandService todoCommandService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("실행예정인 투두를 생성할 수 있다.")
    @Test
    public void 실행예정_투두_생성_테스트() {
        //given
        TodoSaveOrUpdateRequest todoSaveOrUpdateRequest = new TodoSaveOrUpdateRequest("name", false);

        //when
        TodoDetailResponse todoSaveOrUpdateResponse = todoCommandService.saveTodo(todoSaveOrUpdateRequest);

        //then
        Assertions.assertThat(todoSaveOrUpdateResponse.getId()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getName()).isEqualTo(todoSaveOrUpdateRequest.getTodoName());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompleted()).isEqualTo(todoSaveOrUpdateRequest.getTodoCompleted());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompletedAt()).isNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getCreatedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getUpdatedAt()).isNotNull();

    }

    @DisplayName("실행된 투두를 생성할 수 있다.")
    @Test
    public void 실행된_투두_생성_테스트() {
        //given
        TodoSaveOrUpdateRequest todoSaveOrUpdateRequest = new TodoSaveOrUpdateRequest("name", true);

        //when
        TodoDetailResponse todoSaveOrUpdateResponse = todoCommandService.saveTodo(todoSaveOrUpdateRequest);

        //then
        Assertions.assertThat(todoSaveOrUpdateResponse.getId()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getName()).isEqualTo(todoSaveOrUpdateRequest.getTodoName());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompleted()).isEqualTo(todoSaveOrUpdateRequest.getTodoCompleted());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompletedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getCreatedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getUpdatedAt()).isNotNull();
    }

    @DisplayName("투두를 수정할 수 있다.")
    @Test
    public void 투두_수정_테스트() {
        //given
        Todo todo = todoRepository.save(Todo.createTodo("name", false, null, null));
        TodoSaveOrUpdateRequest todoSaveOrUpdateRequest = new TodoSaveOrUpdateRequest("updatedName", true);

        //when
        TodoDetailResponse todoSaveOrUpdateResponse = todoCommandService.updateTodo(todo.getId(), todoSaveOrUpdateRequest);

        //then
        Assertions.assertThat(todoSaveOrUpdateResponse.getId()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getName()).isEqualTo(todoSaveOrUpdateRequest.getTodoName());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompleted()).isEqualTo(todoSaveOrUpdateRequest.getTodoCompleted());
        Assertions.assertThat(todoSaveOrUpdateResponse.getCompletedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getCreatedAt()).isNotNull();
        Assertions.assertThat(todoSaveOrUpdateResponse.getUpdatedAt()).isNotNull();
    }

    @DisplayName("투두를 삭제할 수 있다.")
    @Test
    public void 투두_삭제_테스트() {
        //given
        Todo todo = todoRepository.save(Todo.createTodo("name", false, null, null));

        //when
        todoCommandService.deleteTodo(todo.getId());

        //then
        List<Todo> allTodos = todoRepository.findAll();
        Assertions.assertThat(allTodos).hasSize(0);
    }
}