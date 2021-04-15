package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteTaskListControllerIntegrationTest extends AbstractApiIntegrationTest {

    private static final String TASK_LIST_NAME = "Task List #1";

    @Test
    public void deleteTaskList() throws Exception {
        EpicDto epic = performCreateEpicAndGetResult();
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        TaskListDto taskList = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME);

        performDeleteTaskListAndValidate(epicUuid, taskList.getTaskListId());
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private void performDeleteTaskListAndValidate(UUID epicUuid, Long taskListId) throws Exception {
        performDeleteTaskList(epicUuid, taskListId)
                .andExpect(status().isOk());
    }
}
