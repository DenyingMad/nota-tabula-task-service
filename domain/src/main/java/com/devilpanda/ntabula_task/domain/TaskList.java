package com.devilpanda.ntabula_task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TASKLIST")
public class TaskList extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "epicId")
    private Epic epic;

    @OneToMany(mappedBy = "taskList")
    private Set<Task> tasks;
}
