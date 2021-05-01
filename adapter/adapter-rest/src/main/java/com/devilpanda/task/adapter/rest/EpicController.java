package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.adapter.rest.dto.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.dto.EpicDto;
import com.devilpanda.task.adapter.rest.dto.TaskDto;
import com.devilpanda.task.adapter.rest.dto.TaskListDto;
import com.devilpanda.task.app.api.EpicService;
import com.devilpanda.task.app.api.TaskService;
import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskList;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/rest/project/epic")
@RequiredArgsConstructor
public class EpicController {
    private final EpicService epicService;
    private final TaskService taskService;
    private final DtoMapper mapper;

    // =-----------------------------------------------------
    // CRUD
    // =-----------------------------------------------------

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskListDto.class)
    })
    @PostMapping("/{epicUuid}/task-list")
    public TaskListDto createTaskList(@PathVariable UUID epicUuid, @RequestBody String taskListName) {
        TaskList taskList = epicService.createTaskList(epicUuid, taskListName);
        return mapper.mapDtoFromTaskList(taskList);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class)
    })
    @PostMapping("/{epicUuid}/task-list/{taskListId}/task")
    public TaskDto createTask(@PathVariable UUID epicUuid, @PathVariable Long taskListId, @RequestBody String taskName) {
        Task task = taskService.createTask(epicUuid, taskListId, taskName);
        return mapper.mapDtoFromTask(task);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping()
    public CollectionResponseDto<EpicDto> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        List<EpicDto> epicDtos = epics.stream()
                .map(mapper::mapDtoFromEpic)
                .collect(toList());
        return new CollectionResponseDto<>(epicDtos);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpicDto.class),
            @ApiResponse(code = 404, message = "Epic not found", response = String.class)
    })
    @PutMapping("/{epicUuid}/rename/{name}")
    public EpicDto updateName(@PathVariable UUID epicUuid, @PathVariable String name) {
        Epic epic = epicService.renameEpic(epicUuid, name);
        return mapper.mapDtoFromEpic(epic);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskListDto.class),
            @ApiResponse(code = 404, message = "Task List not found", response = String.class)
    })
    @PutMapping("/{epicUuid}/task-list/{taskListId}/rename/{name}")
    public void updateTaskListName(@PathVariable UUID epicUuid, @PathVariable Long taskListId, @PathVariable String name) {
        epicService.renameTaskList(epicUuid, taskListId, name);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{epicUuid}")
    public void deleteEpic(@PathVariable UUID epicUuid) {
        epicService.deleteEpicByUuid(epicUuid);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{epicUuid}/task-list/{taskListId}")
    public void deleteTaskList(@PathVariable UUID epicUuid, @PathVariable Long taskListId) {
        epicService.deleteTaskList(epicUuid, taskListId);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{epicUuid}/task-list/{taskListId}/task/{taskId}")
    public void deleteTask(@PathVariable UUID epicUuid, @PathVariable Long taskListId, @PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }
}
