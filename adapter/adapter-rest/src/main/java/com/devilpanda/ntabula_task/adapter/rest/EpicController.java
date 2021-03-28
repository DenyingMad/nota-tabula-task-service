package com.devilpanda.ntabula_task.adapter.rest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rest/epic")
public class EpicController {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping()
    public List<EpicDto> getAllEpics() {
        List<EpicDto> list = new ArrayList<>();
        list.add(new EpicDto());
        return list;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpicDto.class)
    })
    @GetMapping("/{uuid}")
    public EpicDto getEpic(@PathVariable UUID uuid) {
        return new EpicDto();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TaskDto.class)
    })
    @GetMapping("/{uuid}/task/{id}")
    public TaskDto getTask(@PathVariable UUID uuid, @PathVariable Long id) {
        return new TaskDto();
    }
}