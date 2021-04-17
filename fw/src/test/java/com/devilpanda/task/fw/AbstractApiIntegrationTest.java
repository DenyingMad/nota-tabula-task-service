package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
import com.devilpanda.task.domain.TaskPriority;
import com.devilpanda.task.domain.TaskStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles("test")
public class AbstractApiIntegrationTest {
    protected static final String TASK_LIST_NAME = "Task List #1";
    protected static final String TASK_NAME = "Task #1";

    protected EpicDto epic;
    protected TaskListDto taskList;
    protected TaskDto task;

    @Autowired
    protected MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        epic = performCreateEpicAndGetResult();
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        taskList = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME);

        task = performCreateTaskAndGetResponse(epicUuid, taskList.getTaskListId(), TASK_NAME);
    }

    protected ResultActions performCreateEpic() throws Exception {
        return this.mvc.perform(post("/api/rest/epic"));
    }

    protected EpicDto performCreateEpicAndGetResult() throws Exception {
        MockHttpServletResponse response = performCreateEpic().
                andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performCreateTaskList(UUID epicId, String taskListName) throws Exception {
        return this.mvc.perform(post("/api/rest/epic/" + epicId + "/task-list")
                .content(taskListName));
    }

    protected TaskListDto performCreateTaskListAndGetResult(UUID epicId, String taskListName) throws Exception {
        MockHttpServletResponse response = performCreateTaskList(epicId, taskListName)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performCreateTask(UUID epicId, Long taskListId, String taskName) throws Exception {
        return this.mvc.perform(post("/api/rest/epic/" + epicId + "/task-list/" + taskListId + "/task")
                .content(taskName));
    }

    protected TaskDto performCreateTaskAndGetResponse(UUID epicId, Long taskListId, String taskName) throws Exception {
        MockHttpServletResponse response = performCreateTask(epicId, taskListId, taskName)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected CollectionResponseDto<EpicDto> performGetAllEpicsAndGetResult() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(get("/api/rest/epic"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performDeleteEpicByUuid(UUID uuid) throws Exception {
        return this.mvc.perform(delete("/api/rest/epic/" + uuid));
    }

    protected ResultActions performDeleteTaskList(UUID epicId, Long taskListId) throws Exception {
        return this.mvc.perform(delete("/api/rest/epic/" + epicId + "/task-list/" + taskListId));
    }

    protected ResultActions performDeleteTask(UUID epicId, Long taskListId, UUID taskId) throws Exception {
        return this.mvc.perform(
                delete("/api/rest/epic/" + epicId + "/task-list/" + taskListId + "/task/" + taskId));
    }

    protected ResultActions performUpdateTaskPriority(UUID taskUuid, TaskPriority priority) throws Exception {
        return this.mvc.perform(put("/api/rest/task/" + taskUuid + "/priority/" + priority));
    }

    protected TaskDto performUpdateTaskPriorityAndGetResult(UUID taskUuid, TaskPriority priority) throws Exception {
        MockHttpServletResponse response = performUpdateTaskPriority(taskUuid, priority)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performUpdateTaskStatus(UUID taskUuid, TaskStatus taskStatus) throws Exception {
        return this.mvc.perform(put("/api/rest/task/" + taskUuid + "/status/" + taskStatus));
    }

    protected TaskDto performUpdateTaskStatusAndGetResponse(UUID taskUuid, TaskStatus taskStatus) throws Exception {
        MockHttpServletResponse response = performUpdateTaskStatus(taskUuid, taskStatus)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private <T> T getDtoFromResponse(MockHttpServletResponse response, TypeReference<T> dtoClass) throws Exception {
        String json = response.getContentAsString();
        return objectMapper.readValue(json, dtoClass);
    }
}
