package com.devilpanda.ntabula_task.fw;

import com.devilpanda.ntabula_task.adapter.rest.CollectionResponseDto;
import com.devilpanda.ntabula_task.adapter.rest.EpicDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetAllEpicsControllerIntegrationTest extends AbstractApiIntegrationTest {

    @Test
    public void getAllEpics() throws Exception {
        EpicDto epic1 = performCreateEpic();
        EpicDto epic2 = performCreateEpic();

        CollectionResponseDto<EpicDto> responseDto = performGetAllEpics();

        Assertions.assertNotNull(responseDto.getData());
        Assertions.assertNotNull(responseDto.getData().get(0).getEpicId());
    }
}
