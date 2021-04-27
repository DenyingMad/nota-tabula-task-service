package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.dto.EpicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GetAllEpicsControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void getAllEpics() throws Exception {
        UUID projectUuid = UUID.fromString(project.getProjectId());

        EpicDto epic1 = performCreateEpicAndGetResult(projectUuid);
        EpicDto epic2 = performCreateEpicAndGetResult(projectUuid);

        CollectionResponseDto<EpicDto> responseDto = performGetAllEpicsAndGetResult();

        Assertions.assertNotNull(responseDto.getData());
        Assertions.assertNotNull(responseDto.getData().get(0).getEpicId());
    }
}
