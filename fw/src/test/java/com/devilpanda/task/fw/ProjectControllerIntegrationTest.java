package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.dto.ProjectDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectControllerIntegrationTest extends AbstractApiIntegrationTest {
    private static final String USER_LOGIN_PETROV = "petrov";
    private static final String USER_LOGIN_VASILIEV = "vasiliev";

    @Test
    public void createProject() throws Exception {
        ProjectDto project = performCreateProjectAndGetResult(getProjectCreateRequest(), USER_LOGIN_IVANOV);

        assertNotNull(UUID.fromString(project.getProjectId()));
        assertEquals(PROJECT_NAME, project.getProjectName());
        assertEquals(PROJECT_DESCRIPTION, project.getProjectDescription());
        assertEquals(true, project.getIsPersonal());
    }

    @Test
    public void getPersonalProjects_success() throws Exception {
        performCreateProject(getProjectCreateRequest(), USER_LOGIN_PETROV);

        CollectionResponseDto<ProjectDto> result = performGetPersonalProjectsAndGetResult(USER_LOGIN_PETROV);

        assertEquals(1, result.getData().size());
        assertEquals(true, result.getData().get(0).getIsPersonal());
    }

    @Test
    public void getPersonalProjects_noProjects() throws Exception {
        performCreateProject(getProjectCreateRequest(), USER_LOGIN_IVANOV);

        CollectionResponseDto<ProjectDto> result = performGetPersonalProjectsAndGetResult(USER_LOGIN_VASILIEV);

        assertEquals(0, result.getData().size());
    }
}
