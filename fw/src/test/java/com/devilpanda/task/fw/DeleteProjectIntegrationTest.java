package com.devilpanda.task.fw;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteProjectIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void deleteProject() throws Exception {
        UUID projectUuid = UUID.fromString(project.getProjectId());

        performDeleteProject(projectUuid)
                .andExpect(status().isOk());
    }
}
