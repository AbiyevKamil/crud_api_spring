package com.kamilabiyev.todoapp.persistence.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "content")
    @NonNull
    private String content;

    @Column(name = "is_completed", columnDefinition = "bit default 0")
    @ColumnDefault("0")
    private Boolean isCompleted;

    @Column(name = "is_deleted", columnDefinition = "bit default 0")
    @ColumnDefault("0")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    public void setInitial() {
        if (isCompleted == null) setIsCompleted(false);
        if (isDeleted == null) setIsDeleted(false);
    }
}
