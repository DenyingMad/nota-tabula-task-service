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

import static java.util.stream.Collectors.toList;

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
    public ProjectDto createProject(@RequestHeader("userLogin") String ownerId,
                                    @RequestBody ProjectDto projectCreateRequest) {
        if (isProjectCreateRequestValid(projectCreateRequest)) {
            Project project = projectService.createProject(ownerId, mapper.mapProjectFromDto(projectCreateRequest));
            return mapper.mapDtoFromProject(project);
        } else {
            throw new IllegalArgumentException("Invalid project create request -> " + "OwnerId: " + ownerId
                    + "\nRequest: " + projectCreateRequest);
        }
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
    @GetMapping("/user/personal")
    public CollectionResponseDto<ProjectDto> getPersonalProjects(@RequestHeader String userLogin) {
        List<Project> projects = projectService.getAllPersonalProjects(userLogin);
        return new CollectionResponseDto<>(projects.stream()
                .map(mapper::mapDtoFromProject)
                .collect(toList()));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CollectionResponseDto.class)
    })
    @GetMapping("/user/organization")
    public CollectionResponseDto<ProjectDto> getOrganizationProjectsWhereUserIsMember(@RequestHeader String userLogin) {
        List<Project> projects = projectService.getAllOrganizationProjectsWhereUserIsMember(userLogin);
        return new CollectionResponseDto<>(projects.stream()
                .map(mapper::mapDtoFromProject)
                .collect(toList()));
    }

    @GetMapping("/user/current")
    public CollectionResponseDto<ProjectDto> getOrganizationProjectsInWork() {
        return null;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{projectUuid}")
    public void deleteProject(@PathVariable UUID projectUuid) {
        projectService.deleteProjectByUuid(projectUuid);
    }

    // =-----------------------------------------------------
    // = Impl
    // =-----------------------------------------------------

    private boolean isProjectCreateRequestValid(ProjectDto projectCreateRequest) {
        return !projectCreateRequest.getProjectName().isEmpty();
    }
}
