package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.ProjectDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateProjectControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void createProject() throws Exception {
        ProjectDto project = performCreateProjectAndGetResult();

        assertNotNull(UUID.fromString(project.getProjectId()));
    }
}
