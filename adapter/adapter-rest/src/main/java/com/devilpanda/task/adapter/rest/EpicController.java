package com.devilpanda.task.adapter.rest;

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
@RequestMapping("/api/rest/epic")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;
    private final TaskService taskService;
    private final DtoMapper mapper;

    // =-----------------------------------------------------
    // CRUD
    // =-----------------------------------------------------

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpicDto.class)
    })
    @PostMapping()
    public EpicDto createEpic() {
        Epic epic = epicService.createEpic();
        return mapper.mapDtoFromEpic(epic);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskListDto.class)
    })
    @PostMapping("/{uuid}/task-list")
    public TaskListDto createTaskList(@PathVariable UUID uuid, @RequestBody String taskListName) {
        TaskList taskList = epicService.createTaskList(uuid, taskListName);
        return mapper.mapDtoFromTaskList(taskList);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class)
    })
    @PostMapping("/{uuid}/task-list/{taskListId}/task")
    public TaskDto createTask(@PathVariable UUID uuid, @PathVariable Long taskListId, @RequestBody String taskName) {
        Task task = taskService.createTask(uuid, taskListId, taskName);
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
            @ApiResponse(code = 200, message = "OK", response = EpicDto.class)
    })
    @GetMapping("/{uuid}")
    public EpicDto getEpic(@PathVariable UUID uuid) {
        return null;//epic();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class)
    })
    @GetMapping("/{uuid}/task/{id}")
    public TaskDto getTask(@PathVariable UUID uuid, @PathVariable Long id) {
        return null;//epic().getTaskLists().get(0).getTasks().get(0);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/{uuid}/delete")
    public void deleteEpic(@PathVariable UUID uuid) {
        epicService.deleteEpicByUuid(uuid);
    }
}