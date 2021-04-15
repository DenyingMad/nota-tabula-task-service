package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteEpicControllerIntegrationTest extends AbstractApiIntegrationTest {
    private UUID epicUuid;
    private Long taskListId1;
    private UUID taskUuid1;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        EpicDto epic = performCreateEpicAndGetResult();
        epicUuid = UUID.fromString(epic.getEpicId());

        TaskListDto taskList1 = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME_1);
        taskListId1 = taskList1.getTaskListId();

        TaskDto task1 = performCreateTaskAndGetResponse(epicUuid, taskList1.getTaskListId(), TASK_NAME_1);
        taskUuid1 = UUID.fromString(task1.getTaskId());
    }

    @Test
    public void deleteEpic_empty() throws Exception {
        performDeleteEpicAndValidateResponse(epicUuid);
    }

    @Test
    public void deleteTaskList() throws Exception {
        performDeleteTaskListAndValidateResponse(epicUuid, taskListId1);
    }

    @Test
    public void deleteTask() throws Exception {
        performDeleteTaskAndValidateResponse(epicUuid, taskListId1, taskUuid1);
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
