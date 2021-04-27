package com.devilpanda.task.app.impl;

import com.devilpanda.task.adapter.jpa.ProjectRepository;
import com.devilpanda.task.app.api.ProjectService;
import com.devilpanda.task.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject() {
        Project project = new Project();
        project.setUuid(UUID.randomUUID());

        return projectRepository.saveAndFlush(project);
    }
}
