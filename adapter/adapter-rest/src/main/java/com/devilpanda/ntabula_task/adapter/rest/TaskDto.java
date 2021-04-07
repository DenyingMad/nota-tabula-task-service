package com.devilpanda.ntabula_task.adapter.rest;

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
    private Integer priority;
    private Boolean checked;
}
