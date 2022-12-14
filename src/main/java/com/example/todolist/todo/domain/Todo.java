package com.example.todolist.todo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String name;

    private Boolean completed;

    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Todo(String name, Boolean completed, LocalDateTime completedAt, User user) {
        this.name = name;
        this.completed = completed;
        this.completedAt = completedAt;
        this.user = user;
    }

    public static Todo createTodo(String name, Boolean completed, LocalDateTime completedAt, User user) {
        return new Todo(name, completed, completedAt, user);
    }

    public void updateTodo(String name, Boolean completed, LocalDateTime completedAt) {
        this.name = name;
        this.completed = completed;
        this.completedAt = completedAt;
    }
}
