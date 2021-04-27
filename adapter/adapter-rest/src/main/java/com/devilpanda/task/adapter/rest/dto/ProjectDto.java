package com.devilpanda.task.adapter.rest.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Данные проекта")
public class ProjectDto {
    private String projectId;
    private String projectName;
    private String projectDescription;
    private List<EpicDto> epics;
}
