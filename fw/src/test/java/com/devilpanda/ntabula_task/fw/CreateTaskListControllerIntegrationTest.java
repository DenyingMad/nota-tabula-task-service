package com.devilpanda.ntabula_task.fw;

import com.devilpanda.ntabula_task.adapter.rest.EpicDto;
import com.devilpanda.ntabula_task.adapter.rest.TaskListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTaskListControllerIntegrationTest extends AbstractApiIntegrationTest {

    private static final String TASK_LIST_NAME = "New Task List";

    @Test
    public void createTaskList() throws Exception {
        EpicDto epicDto = performCreateEpic();

        TaskListDto taskList = performCreateTaskList(epicDto.getEpicId(), TASK_LIST_NAME);

        Assertions.assertEquals(TASK_LIST_NAME, taskList.getTaskListName());
    }
}
