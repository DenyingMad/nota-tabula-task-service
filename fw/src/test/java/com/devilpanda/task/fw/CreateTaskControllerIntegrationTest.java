package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTaskControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void createTask() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        Long taskListId = taskList.getTaskListId();

        TaskDto actualTask = performCreateTaskAndGetResponse(epicUuid, taskListId, TASK_NAME);

        assertEquals(TASK_NAME, actualTask.getTaskName());
        assertNotNull(UUID.fromString(actualTask.getTaskId()));
    }
}
