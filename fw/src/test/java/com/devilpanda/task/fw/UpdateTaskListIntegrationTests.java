package com.devilpanda.task.fw;

import com.devilpanda.task.domain.TaskList;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

public class UpdateTaskListIntegrationTests extends AbstractApiIntegrationTest{
    private static final String UPDATED_NAME = "Renamed Task List";

    private UUID epicUuid;
    private Long taskListId;

    @Test
    public void updateTaskList_rename() throws Exception {
        epicUuid = UUID.fromString(epic.getEpicId());
        taskListId = taskList.getTaskListId();

        ResultActions resultActions = performRenameTaskList(epicUuid, taskListId, UPDATED_NAME);

        resultActions.andExpect(status().isOk());
        assertTaskListNameIs(UPDATED_NAME);
    }

    @Test
    public void updateTaskList_rename_taskListNotFound() throws Exception {
        epicUuid = UUID.fromString(epic.getEpicId());
        taskListId = new RandomDataGenerator().nextLong(-2423523L, -1L);

        ResultActions response = performRenameTaskList(epicUuid, taskListId, UPDATED_NAME);

        response.andExpect(status().isNotFound());
    }

    @Test
    void updateTaskList_rename_EpicNotFound() throws Exception {
        epicUuid = UUID.randomUUID();
        taskListId = taskList.getTaskListId();

        ResultActions response = performRenameTaskList(epicUuid, taskListId, UPDATED_NAME);

        response.andExpect(status().isNotFound());
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private void assertTaskListNameIs(String updatedName) {
        TaskList taskList = taskListRepository.findByEpic_UuidAndId(epicUuid, taskListId).get();
        assertEquals(UPDATED_NAME, taskList.getName());
    }
}
