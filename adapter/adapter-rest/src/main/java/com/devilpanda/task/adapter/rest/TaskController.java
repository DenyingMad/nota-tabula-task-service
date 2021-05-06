package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.adapter.rest.dto.TaskDto;
import com.devilpanda.task.app.api.TaskService;
import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskPriority;
import com.devilpanda.task.domain.TaskStatus;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rest/project/task/{taskUuid}")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final DtoMapper mapper;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class),
            @ApiResponse(code = 404, message = "Task not found", response = String.class)
    })
    @PutMapping("/priority/{priority}")
    public TaskDto updatePriority(@PathVariable UUID taskUuid, @PathVariable TaskPriority priority) {
        Task task = taskService.updateTaskPriority(taskUuid, priority);
        return mapper.mapDtoFromTask(task);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class),
            @ApiResponse(code = 404, message = "Task not found", response = String.class),
            @ApiResponse(code = 409, message = "Action rejected due to business rules / validations", response = String.class)
    })
    @PutMapping("/status/{status}")
    public TaskDto updateStatus(@PathVariable UUID taskUuid, @PathVariable TaskStatus status) {
        Task task = taskService.updateTaskStatus(taskUuid, status);
        return mapper.mapDtoFromTask(task);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class),
            @ApiResponse(code = 404, message = "Task not found", response = String.class)
    })
    @PutMapping("/rename/{name}")
    public TaskDto updateName(@PathVariable UUID taskUuid, @PathVariable String name) {
        Task task = taskService.renameTask(taskUuid, name);
        return mapper.mapDtoFromTask(task);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class),
            @ApiResponse(code = 404, message = "Task not found", response = String.class)
    })
    @GetMapping()
    public TaskDto getTask(@PathVariable UUID taskUuid) {
        Task task = taskService.getTask(taskUuid);
        return mapper.mapDtoFromTask(task);
    }
}
