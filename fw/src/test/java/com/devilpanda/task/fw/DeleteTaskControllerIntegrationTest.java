package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class DeleteTaskControllerIntegrationTest extends AbstractApiIntegrationTest {
    private static final String TASK_LIST_NAME = "Task List #1";
    private static final String TASK_NAME = "Task #1";

    @Test
    public void deleteTask() throws Exception {
        EpicDto epic = performCreateEpicAndGetResult();
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        TaskListDto taskList = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME);
        TaskDto task = performCreateTaskAndGetResponse(epicUuid, taskList.getTaskListId(), TASK_NAME);
        UUID taskUuid = UUID.fromString(task.getTaskId());

        performDeleteTaskAndValidateResponse(epicUuid, taskList.getTaskListId(), taskUuid);
    }

    private void performDeleteTaskAndValidateResponse(UUID epicUuid, Long taskListId, UUID taskUuid) throws Exception {
        performDeleteTask(epicUuid, taskListId, taskUuid);
    }
}
