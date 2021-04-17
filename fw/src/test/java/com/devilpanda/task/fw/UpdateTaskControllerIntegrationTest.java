package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static com.devilpanda.task.domain.TaskPriority.HIGH;
import static com.devilpanda.task.domain.TaskPriority.LOW;
import static com.devilpanda.task.domain.TaskStatus.IN_PROGRESS;
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
    public void updateTask_setPriority_samePriority_success() throws Exception {
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

    @Test
    public void updateTask_setStatus_success() throws Exception {
        UUID taskUuid = UUID.fromString(task.getTaskId());

        TaskDto task = performUpdateTaskStatusAndGetResponse(taskUuid, IN_PROGRESS);

        assertEquals(IN_PROGRESS, task.getStatus());
    }

    @Test
    public void updateTask_setStatus_sameStatusError() throws Exception {
        UUID taskUuid = UUID.fromString(task.getTaskId());
        performUpdateTaskStatus(taskUuid, IN_PROGRESS);

        ResultActions resultActions = performUpdateTaskStatus(taskUuid, IN_PROGRESS);

        resultActions.andExpect(status().isConflict());
    }

    @Test
    public void updateTask_setStatus_taskNotFound() throws Exception {
        UUID taskUuid = UUID.randomUUID();

        ResultActions resultActions = performUpdateTaskStatus(taskUuid, IN_PROGRESS);

        resultActions.andExpect(status().isNotFound());
    }
}
