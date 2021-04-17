package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.domain.TaskPriority;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Задача")
public class TaskDto {
    private String taskId;
    private String taskName;
    private String taskDescription;
    // todo поменять на MemberDto
    private Long assigned;
    private TaskPriority priority;
    private Boolean checked;
}
