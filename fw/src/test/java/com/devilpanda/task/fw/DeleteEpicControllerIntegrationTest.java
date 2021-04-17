package com.devilpanda.task.fw;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteEpicControllerIntegrationTest extends AbstractApiIntegrationTest {
    @Test
    public void deleteEpic_empty() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        performDeleteEpicAndValidateResponse(epicUuid);
    }

    @Test
    public void deleteTaskList() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        Long taskListId = taskList.getTaskListId();

        performDeleteTaskListAndValidateResponse(epicUuid, taskListId);
    }

    @Test
    public void deleteTask() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        Long taskListId = taskList.getTaskListId();
        UUID taskUuid = UUID.fromString(task.getTaskId());

        performDeleteTaskAndValidateResponse(epicUuid, taskListId, taskUuid);
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private void performDeleteEpicAndValidateResponse(UUID epicUuid) throws Exception {
        performDeleteEpicByUuid(epicUuid)
                .andExpect(status().isOk());
    }

    private void performDeleteTaskListAndValidateResponse(UUID epicUuid, Long taskListId) throws Exception {
        performDeleteTaskList(epicUuid, taskListId)
                .andExpect(status().isOk());
    }

    private void performDeleteTaskAndValidateResponse(UUID epicUuid, Long taskListId, UUID taskUuid) throws Exception {
        performDeleteTask(epicUuid, taskListId, taskUuid);
    }
}
