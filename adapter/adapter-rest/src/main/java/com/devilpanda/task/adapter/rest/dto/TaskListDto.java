package com.devilpanda.task.adapter.rest.dto;

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
    private Long taskListId;
    private String taskListName;
    private List<TaskDto> tasks;
}
