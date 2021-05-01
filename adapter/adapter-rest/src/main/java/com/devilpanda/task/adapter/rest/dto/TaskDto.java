package com.devilpanda.task.adapter.rest.dto;

import com.devilpanda.task.domain.TaskPriority;
import com.devilpanda.task.domain.TaskStatus;
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
    private MemberDto assigned;
    private TaskPriority priority;
    private TaskStatus status;
}
