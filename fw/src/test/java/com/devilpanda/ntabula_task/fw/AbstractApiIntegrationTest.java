package com.devilpanda.ntabula_task.fw;

import com.devilpanda.ntabula_task.adapter.rest.CollectionResponseDto;
import com.devilpanda.ntabula_task.adapter.rest.EpicDto;
import com.devilpanda.ntabula_task.adapter.rest.TaskDto;
import com.devilpanda.ntabula_task.adapter.rest.TaskListDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    public EpicDto performCreateEpic() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(post("/api/rest/epic"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    public TaskListDto performCreateTaskList(String epicId, String taskListName) throws Exception {
        MockHttpServletResponse response = this.mvc.perform(post("/api/rest/epic/" + epicId + "/task-list")
                .content(taskListName))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    public TaskDto performCreateTask(String epicId, Long taskListId, String taskName) throws Exception {
        MockHttpServletResponse response = this.mvc.perform(
                post("/api/rest/epic/" + epicId + "/task-list/" + taskListId + "/task")
                        .content(taskName))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        return getDtoFromResponse(response, new TypeReference<>() {
        });
    }

    public CollectionResponseDto<EpicDto> performGetAllEpics() throws Exception {
        MockHttpServletResponse response = this.mvc.perform(get("/api/rest/epic"))
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
