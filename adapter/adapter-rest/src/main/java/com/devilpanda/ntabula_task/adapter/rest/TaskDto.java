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
    private String uuid;
    private String name;
    private String description;
    private MemberDto assigned;
    private Integer priority;
    private Boolean checked;
}
