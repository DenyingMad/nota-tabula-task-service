package com.devilpanda.task.fw;

import com.devilpanda.task.adapter.rest.CollectionResponseDto;
import com.devilpanda.task.adapter.rest.EpicDto;
import com.devilpanda.task.adapter.rest.TaskDto;
import com.devilpanda.task.adapter.rest.TaskListDto;
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

    @Autowired
    protected MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
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

    protected ResultActions performCreateTaskList(String epicId, String taskListName) throws Exception {
        return this.mvc.perform(post("/api/rest/epic/" + epicId + "/task-list")
                .content(taskListName));
    }

    protected TaskListDto performCreateTaskListAndGetResult(String epicId, String taskListName) throws Exception {
        MockHttpServletResponse response = performCreateTaskList(epicId, taskListName)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    protected ResultActions performCreateTask(String epicId, Long taskListId, String taskName) throws Exception {
        return this.mvc.perform(post("/api/rest/epic/" + epicId + "/task-list/" + taskListId + "/task")
                .content(taskName));
    }

    protected TaskDto performCreateTaskAndGetResponse(String epicId, Long taskListId, String taskName) throws Exception {
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

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private <T> T getDtoFromResponse(MockHttpServletResponse response, TypeReference<T> dtoClass) throws Exception {
        String json = response.getContentAsString();
        return objectMapper.readValue(json, dtoClass);
    }
}
