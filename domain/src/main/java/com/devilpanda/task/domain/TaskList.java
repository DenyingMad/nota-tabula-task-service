package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TASKLIST")
public class TaskList extends BaseEntity {
    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "epicid")
    private Epic epic;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.REMOVE)
    private Set<Task> tasks;
}
