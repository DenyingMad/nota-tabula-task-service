package com.devilpanda.task.app.impl;

import com.devilpanda.task.adapter.jpa.ProjectRepository;
import com.devilpanda.task.app.api.ProjectService;
import com.devilpanda.task.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject() {
        Project project = new Project();
        project.setUuid(UUID.randomUUID());
        project.setName("New project");

        return projectRepository.saveAndFlush(project);
    }

    @Override
    public List<Project> getAllProjects() {
        // todo возвращать только те проекты, к которым текущий пользователь иммет доступ
        return projectRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteProjectByUuid(UUID projectUuid) {
        projectRepository.deleteProjectByUuid(projectUuid);
    }
}
