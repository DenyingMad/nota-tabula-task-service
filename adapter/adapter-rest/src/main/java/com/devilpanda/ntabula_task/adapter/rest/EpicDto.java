package com.devilpanda.ntabula_task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Данные эпика")
public class EpicDto {
    private String epicName;
    private String description;
    private DetailsDto details;
    private List<TaskListDto> taskLists;
    private List<MemberDto> members;
}
