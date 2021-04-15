package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteEpicControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void deleteEpic() throws Exception {
        EpicDto epic = performCreateEpicAndGetResult();
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        deleteEpicAndValidateStatus(epicUuid);
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private void deleteEpicAndValidateStatus(UUID epicUuid) throws Exception {
        performDeleteEpicByUuid(epicUuid)
                .andExpect(status().isOk());
    }
}
