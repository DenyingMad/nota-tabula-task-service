package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.jpa.TaskListRepository;
import com.devilpanda.task.adapter.rest.dto.*;
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
    private static final String BASE_URL = "/api/rest/project";
    protected static final String TASK_LIST_NAME = "Task List #1";
    protected static final String TASK_NAME = "Task #1";

    protected ProjectDto project;
    protected EpicDto epic;
    protected TaskListDto taskList;
    protected TaskDto task;

    @Autowired
    protected MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    protected TaskListRepository taskListRepository;

    @BeforeEach
    public void setUp() throws Exception {
        project = performCreateProjectAndGetResult();
        epic = performCreateEpicAndGetResult(UUID.fromString(project.getProjectId()));
        UUID epicUuid = UUID.fromString(epic.getEpicId());

        taskList = performCreateTaskListAndGetResult(epicUuid, TASK_LIST_NAME);

        task = performCreateTaskAndGetResponse(epicUuid, taskList.getTaskListId(), TASK_NAME);
    }

    protected ResultActions performCreateProject() throws Exception {
        return this.mvc.perform(post(BASE_URL)
                .header("userLogin", "user"));
    }

    protected ProjectDto performCreateProjectAndGetResult() throws Exception {
        MockHttpServletResponse response = performCreateProject()
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performCreateEpic(UUID projectUuid) throws Exception {
        return this.mvc.perform(post(BASE_URL + "/" + projectUuid + "/epic"));
    }

    protected EpicDto performCreateEpicAndGetResult(UUID projectUuid) throws Exception {
        MockHttpServletResponse response = performCreateEpic(projectUuid).
                andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performCreateTaskList(UUID epicId, String taskListName) throws Exception {
        return this.mvc.perform(post(BASE_URL + "/epic/" + epicId + "/task-list")
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
        return this.mvc.perform(post(BASE_URL + "/epic/" + epicId + "/task-list/" + taskListId + "/task")
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
        MockHttpServletResponse response = this.mvc.perform(get(BASE_URL + "/epic"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performGetTask(UUID taskUuid) throws Exception {
        return this.mvc.perform(get(BASE_URL + "/task/" + taskUuid));
    }

    protected TaskDto performGetTaskAndGetResult(UUID taskUuid) throws Exception {
        MockHttpServletResponse response = performGetTask(taskUuid)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performDeleteEpicByUuid(UUID uuid) throws Exception {
        return this.mvc.perform(delete(BASE_URL + "/epic/" + uuid));
    }

    protected ResultActions performDeleteTaskList(UUID epicId, Long taskListId) throws Exception {
        return this.mvc.perform(delete(BASE_URL + "/epic/" + epicId + "/task-list/" + taskListId));
    }

    protected ResultActions performDeleteTask(UUID epicId, Long taskListId, UUID taskId) throws Exception {
        return this.mvc.perform(
                delete(BASE_URL + "/epic/" + epicId + "/task-list/" + taskListId + "/task/" + taskId));
    }

    protected ResultActions performUpdateTaskPriority(UUID taskUuid, TaskPriority priority) throws Exception {
        return this.mvc.perform(put(BASE_URL + "/task/" + taskUuid + "/priority/" + priority));
    }

    protected TaskDto performUpdateTaskPriorityAndGetResult(UUID taskUuid, TaskPriority priority) throws Exception {
        MockHttpServletResponse response = performUpdateTaskPriority(taskUuid, priority)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performUpdateTaskStatus(UUID taskUuid, TaskStatus taskStatus) throws Exception {
        return this.mvc.perform(put(BASE_URL + "/task/" + taskUuid + "/status/" + taskStatus));
    }

    protected TaskDto performUpdateTaskStatusAndGetResponse(UUID taskUuid, TaskStatus taskStatus) throws Exception {
        MockHttpServletResponse response = performUpdateTaskStatus(taskUuid, taskStatus)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performRenameTask(UUID taskUuid, String name) throws Exception {
        return this.mvc.perform(put(BASE_URL + "/task/" + taskUuid + "/rename/" + name));
    }

    protected TaskDto performRenameTaskAndGetResponse(UUID taskUuid, String name) throws Exception {
        MockHttpServletResponse response = performRenameTask(taskUuid, name)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performRenameEpic(UUID epicUuid, String name) throws Exception {
        return this.mvc.perform(put(BASE_URL + "/epic/" + epicUuid + "/rename/" + name));
    }

    protected EpicDto performRenameEpicAndGetResponse(UUID epicUuid, String name) throws Exception {
        MockHttpServletResponse response = performRenameEpic(epicUuid, name)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performRenameTaskList(UUID epicUuid, Long taskListId, String name) throws Exception {
        return this.mvc.perform(put(BASE_URL + "/epic/" + epicUuid + "/task-list/" + taskListId + "/rename/"+ name));
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private <T> T getDtoFromResponse(MockHttpServletResponse response, TypeReference<T> dtoClass) throws Exception {
        String json = response.getContentAsString();
        return objectMapper.readValue(json, dtoClass);
    }
}
