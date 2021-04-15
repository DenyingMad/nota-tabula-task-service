package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTaskListControllerIntegrationTest extends AbstractApiIntegrationTest {

    private static final String TASK_LIST_NAME = "New Task List";

    @Test
    public void createTaskList() throws Exception {
        EpicDto epicDto = performCreateEpicAndGetResult();

        TaskListDto taskList = performCreateTaskListAndGetResult(epicDto.getEpicId(), TASK_LIST_NAME);

        Assertions.assertEquals(TASK_LIST_NAME, taskList.getTaskListName());
    }
}
