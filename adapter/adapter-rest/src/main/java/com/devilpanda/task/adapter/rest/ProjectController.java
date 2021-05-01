package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.adapter.rest.dto.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.dto.EpicDto;
import com.devilpanda.task.adapter.rest.dto.ProjectDto;
import com.devilpanda.task.app.api.EpicService;
import com.devilpanda.task.app.api.ProjectService;
import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.Project;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping()
    public CollectionResponseDto<ProjectDto> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDto> projectDtos = projects.stream()
                .map(mapper::mapDtoFromProject)
                .collect(Collectors.toList());
        return new CollectionResponseDto<>(projectDtos);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{projectUuid}")
    public void deleteProject(@PathVariable UUID projectUuid) {
        projectService.deleteProjectByUuid(projectUuid);
    }
}
