package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TASK")
public class Task extends BaseEntity {
    private UUID uuid;
    private String name;
    private String description;
    private Integer priority;
    private Boolean checked;
    private Long assigned;

    @ManyToOne
    @JoinColumn(name = "tasklistid")
    private TaskList taskList;

}
