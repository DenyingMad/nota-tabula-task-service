package com.devilpanda.task.app.impl;

import com.devilpanda.task.adapter.jpa.ProjectRepository;
import com.devilpanda.task.app.api.ProjectService;
import com.devilpanda.task.app.api.UsersService;
import com.devilpanda.task.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UsersService usersService;
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(String ownerId) {
        Project project = new Project();
        project.setUuid(UUID.randomUUID());
        project.setName("New project");
        project.setOwnerId(ownerId);

        return projectRepository.saveAndFlush(project);
    }

    @Override
    public List<Project> getAllPersonalProjects(String userLogin) {
        return projectRepository.findProjectsByOwner(userLogin, true);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getAllOrganizationProjectsWhereUserIsMember(String userLogin) {
        List<String> organizationNames = usersService.getOrganizationsNames(userLogin);


        List<Project> projectList = new ArrayList<>();
        organizationNames.forEach(ownerName -> {
            projectList.addAll(projectRepository.findProjectsByOwner(ownerName, false));
        });
        return projectList;
    }
}
