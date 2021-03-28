package com.devilpanda.ntabula_task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Список задач", parent = EpicDto.class)
public class TaskListDto {
    private String name;
    private List<TaskDto> tasks;
}
