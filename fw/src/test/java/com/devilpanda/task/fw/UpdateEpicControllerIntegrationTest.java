package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateEpicControllerIntegrationTest extends AbstractApiIntegrationTest {
    private static final String UPDATED_NAME = "New Epic Name";

    @Test
    public void updateEpic_rename() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        EpicDto epic = performRenameEpicAndGetResponse(epicUuid, UPDATED_NAME);

        assertEquals(UPDATED_NAME, epic.getDetails().getEpicName());
    }

    @Test
    public void updateEpic_rename_epicNotFound() throws Exception {
        UUID epicUuid = UUID.randomUUID();

        ResultActions resultActions = performRenameEpic(epicUuid, UPDATED_NAME);

        resultActions.andExpect(status().isNotFound());
    }
}
