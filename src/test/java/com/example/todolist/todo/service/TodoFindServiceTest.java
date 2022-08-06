package com.example.todolist.todo.service;

import com.example.todolist.todo.domain.Todo;
import com.example.todolist.todo.dto.TodoDetailResponse;
import com.example.todolist.todo.dto.TodoSimpleResponses;
import com.example.todolist.todo.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class TodoFindServiceTest {

    @Autowired
    private TodoFindService todoFindService;

    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("원하는 투두를 찾을수 있다.")
    @Test
    public void 투두_단건_조회() {
        //given
        Todo savedTodo = todoRepository.save(Todo.createTodo("name", false, null, null));

        //when
        TodoDetailResponse todoDetailResponse = todoFindService.findTodo(savedTodo.getId());

        //then
        Assertions.assertThat(todoDetailResponse.getId()).isNotNull();
        Assertions.assertThat(todoDetailResponse.getName()).isEqualTo(savedTodo.getName());
        Assertions.assertThat(todoDetailResponse.getCompleted()).isEqualTo(savedTodo.getCompleted());
        Assertions.assertThat(todoDetailResponse.getCompletedAt()).isNull();
        Assertions.assertThat(todoDetailResponse.getCreatedAt()).isNotNull();
        Assertions.assertThat(todoDetailResponse.getUpdatedAt()).isNotNull();
    }

    @DisplayName("투두를 리스트를 찾을 수 있다.")
    @Test
    public void 투두_리스트_조회() {
        //given
        Todo savedTodo1 = todoRepository.save(Todo.createTodo("name1", false, null, null));
        Todo savedTodo2 = todoRepository.save(Todo.createTodo("name2", false, null, null));

        PageRequest pageRequest = PageRequest.of(0, 100);

        //when
        TodoSimpleResponses todoSimpleResponses = todoFindService.findTodos(pageRequest, "skip");

        //then
        Assertions.assertThat(todoSimpleResponses.getTodoSimpleResponses()).hasSize(2);
        Assertions.assertThat(todoSimpleResponses.getPageResponse().getCurrentPage()).isEqualTo(0);
        Assertions.assertThat(todoSimpleResponses.getPageResponse().getTotalPageSize()).isEqualTo(1);
    }
}