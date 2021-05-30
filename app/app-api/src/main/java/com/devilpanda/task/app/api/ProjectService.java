package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(String ownerId, Project project);

    List<Project> getAllPersonalProjects(String userLogin);

    List<Project> getAllOrganizationProjectsWhereUserIsMember(String userLogin);
}
