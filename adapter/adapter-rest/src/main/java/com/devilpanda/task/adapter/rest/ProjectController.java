package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.app.api.EpicService;
import com.devilpanda.task.app.api.ProjectService;
import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.Project;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/rest/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final EpicService epicService;
    private final DtoMapper mapper;

    // =-----------------------------------------------------
    // CRUD
    // =-----------------------------------------------------

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProjectDto.class)
    })
    @PostMapping()
    public ProjectDto createProject() {
        Project project = projectService.createProject();
        return mapper.mapDtoFromProject(project);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpicDto.class)
    })
    @PostMapping("/{projectUuid}/epic")
    public EpicDto createEpic(@PathVariable UUID projectUuid) {
        Epic epic = epicService.createEpic(projectUuid);
        return mapper.mapDtoFromEpic(epic);
    }
}
