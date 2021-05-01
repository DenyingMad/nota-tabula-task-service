package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    Project createProject();

    List<Project> getAllProjects();

    void deleteProjectByUuid(UUID projectUuid);
}
