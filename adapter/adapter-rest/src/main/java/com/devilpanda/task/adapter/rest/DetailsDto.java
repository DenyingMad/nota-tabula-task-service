package com.devilpanda.task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Сводная информация по эпику", parent = EpicDto.class)
public class DetailsDto {
    private String epicName;
    private String epicDescription;
    private Integer totalTasks;
    private Integer totalTaskList;
    private List<MemberDto> members;
}
