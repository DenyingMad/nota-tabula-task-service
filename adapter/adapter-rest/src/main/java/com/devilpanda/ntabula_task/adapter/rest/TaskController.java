package com.devilpanda.ntabula_task.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/task")
public class TaskController {

    @GetMapping("/get-all")
    public String getAll() {
        return "All";
    }
}