package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.EpicDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEpicControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void createEpic() throws Exception {
        UUID projectUuid = UUID.fromString(project.getProjectId());

        EpicDto epic = performCreateEpicAndGetResult(projectUuid);

        assertNotNull(UUID.fromString(epic.getEpicId()));
        assertNotNull(epic.getTaskLists().get(0));
    }
}
