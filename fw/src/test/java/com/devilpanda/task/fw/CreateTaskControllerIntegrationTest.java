package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTaskControllerIntegrationTest extends AbstractApiIntegrationTest {

    private static final String TASK_NAME = "Test Task Name";
    private static final String TASK_LIST_NAME = "Test TaskList Name";

    @Test
    public void createTask() throws Exception {
        EpicDto epic = performCreateEpicAndGetResult();
        TaskListDto taskListDto = performCreateTaskListAndGetResult(epic.getEpicId(), TASK_LIST_NAME);

        TaskDto actualTask = performCreateTaskAndGetResponse(epic.getEpicId(), taskListDto.getTaskListId(), TASK_NAME);

        assertEquals(TASK_NAME, actualTask.getTaskName());
        assertNotNull(UUID.fromString(actualTask.getTaskId()));
    }
}
