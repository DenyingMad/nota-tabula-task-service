package com.devilpanda.ntabula_task.fw;

import com.devilpanda.ntabula_task.adapter.rest.EpicDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEpicControllerIntegrationTest extends AbstractApiIntegrationTest {

    @Test
    public void createEpic() throws Exception {
        EpicDto epic = performCreateEpic();

        assertNotNull(UUID.fromString(epic.getEpicId()));
        assertNotNull(epic.getTaskLists().get(0));
    }
}
