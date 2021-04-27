package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetTaskControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void getTask() throws Exception {
        UUID taskUuid = UUID.fromString(task.getTaskId());

        TaskDto responseDto = performGetTaskAndGetResult(taskUuid);

        assertNotNull(responseDto.getTaskId());
        assertEquals(taskUuid, UUID.fromString(responseDto.getTaskId()));
    }

    @Test
    public void getTask_taskNotFound() throws Exception {
        UUID taskUuid = UUID.randomUUID();

        ResultActions response = performGetTask(taskUuid);

        response.andExpect(status().isNotFound());
    }
}
