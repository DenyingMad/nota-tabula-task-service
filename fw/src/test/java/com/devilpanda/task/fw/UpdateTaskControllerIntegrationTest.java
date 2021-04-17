package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static com.devilpanda.task.domain.TaskPriority.HIGH;
import static com.devilpanda.task.domain.TaskPriority.LOW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateTaskControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void updateTask_setPriority_success() throws Exception {
        UUID taskUuid = UUID.fromString(task.getTaskId());

        TaskDto task = performUpdateTaskPriorityAndGetResult(taskUuid, HIGH);

        assertEquals(HIGH, task.getPriority());
    }

    @Test
    public void updateTask_setPriority_success_samePriority() throws Exception {
        UUID taskUuid = UUID.fromString(task.getTaskId());
        performUpdateTaskPriority(taskUuid, HIGH);

        TaskDto task = performUpdateTaskPriorityAndGetResult(taskUuid, HIGH);

        assertEquals(HIGH, task.getPriority());
    }

    @Test
    public void updateTask_setPriority_taskNotFound() throws Exception {
        UUID taskUuid = UUID.randomUUID();

        ResultActions resultActions = performUpdateTaskPriority(taskUuid, LOW);

        resultActions.andExpect(status().isNotFound());
    }
}
