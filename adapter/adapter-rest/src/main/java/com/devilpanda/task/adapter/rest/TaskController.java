package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.app.api.TaskService;
import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskPriority;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/rest")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final DtoMapper mapper;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class),
            @ApiResponse(code = 404, message = "Task not found", response = String.class)
    })
    @PutMapping("/task/{taskUuid}/priority/{priority}")
    public TaskDto updatePriority(@PathVariable UUID taskUuid, @PathVariable TaskPriority priority) {
        Task task = taskService.updateTaskPriority(taskUuid, priority);
        return mapper.mapDtoFromTask(task);
    }
}
