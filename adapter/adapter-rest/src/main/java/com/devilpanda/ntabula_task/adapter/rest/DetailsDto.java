package com.devilpanda.ntabula_task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Сводная информация по эпику", parent = EpicDto.class)
public class DetailsDto {
    private Integer totalTaskList;
    private Integer totalTasks;
}
