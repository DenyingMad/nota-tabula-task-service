package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.dto.ProjectDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAllProjectsIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void getAllProjects() throws Exception {
        ProjectDto projectOne = performCreateProjectAndGetResult();
        ProjectDto projectTwo = performCreateProjectAndGetResult();

        CollectionResponseDto<ProjectDto> responseDto = performGetAllProjectsAndGetResult();

        assertNotNull(responseDto.getData());
        assertNotNull(responseDto.getData().get(0).getProjectId());
    }
}
