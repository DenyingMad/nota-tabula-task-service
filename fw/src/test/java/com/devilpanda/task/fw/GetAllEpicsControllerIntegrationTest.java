package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.EpicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllEpicsControllerIntegrationTest extends AbstractApiIntegrationTest {

    @Test
    public void getAllEpics() throws Exception {
        EpicDto epic1 = performCreateEpicAndGetResult();
        EpicDto epic2 = performCreateEpicAndGetResult();

        CollectionResponseDto<EpicDto> responseDto = performGetAllEpicsAndGetResult();

        Assertions.assertNotNull(responseDto.getData());
        Assertions.assertNotNull(responseDto.getData().get(0).getEpicId());
    }
}
