package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.TaskListDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTaskListControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void createTaskList() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        TaskListDto taskList = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME);

        assertEquals(TASK_LIST_NAME, taskList.getTaskListName());
    }
}
