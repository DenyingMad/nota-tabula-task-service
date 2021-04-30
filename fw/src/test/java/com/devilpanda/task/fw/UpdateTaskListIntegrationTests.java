package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.dto.TaskListDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;
import java.util.UUID;

public class UpdateTaskListIntegrationTests extends AbstractApiIntegrationTest{
    private static final String UPDATED_NAME = "Renamed Task List";

    @Test
    public void updateTaskList_rename() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        Long taskListId = taskList.getTaskListId();

        TaskListDto taskListDto = performRenameTaskListAndGetResult(epicUuid, taskListId, UPDATED_NAME);

        assertEquals(UPDATED_NAME, taskListDto.getTaskListName());
    }

    @Test
    public void updateTaskList_rename_taskListNotFound() throws Exception {
        UUID epicUuid = UUID.fromString(epic.getEpicId());
        Long taskListId = new Random().nextLong();

        ResultActions response = performRenameTaskList(epicUuid, taskListId, UPDATED_NAME);

        response.andExpect(status().isNotFound());
    }

    @Test
    void updateTaskList_rename_EpicNotFound() throws Exception {
        UUID epicUuid = UUID.randomUUID();
        Long taskListId = taskList.getTaskListId();

        ResultActions response = performRenameTaskList(epicUuid, taskListId, UPDATED_NAME);

        response.andExpect(status().isNotFound());
    }
}
