package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.adapter.rest.dto.EpicDto;
import com.devilpanda.task.adapter.rest.dto.ProjectDto;
import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoMapperTest {
    private DtoMapper mapper;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mapper = new DtoMapper();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDefaultPrettyPrinter(new MyDefaultPrettyPrinter());
    }

    @Test
    public void mapDtoFromProject() {
        Project project = objectFromFile("/DtoMapper/Project.json", Project.class);

        ProjectDto res = mapper.mapDtoFromProject(project);

        assertEqualsToFile("/DtoMapper/ProjectDto.json", res);
    }

    @Test
    public void mapProjectFromDto() {
        ProjectDto projectDto = objectFromFile("/DtoMapper/ProjectCreateRequestDto.json", ProjectDto.class);

        Project res = mapper.mapProjectFromDto(projectDto);

        assertEqualsToFile("/DtoMapper/Project_empty.json", res);
    }

    @Test
    public void mapDtoFromEpic_full() {
        Epic epic = objectFromFile("/DtoMapper/Epic_full.json", Epic.class);

        EpicDto res = mapper.mapDtoFromEpic(epic);

        assertEqualsToFile("/DtoMapper/EpicDto_full.json", res);
    }

    @Test
    public void mapDtoFromEpic_empty() {
        Epic epic = objectFromFile("/DtoMapper/Epic_empty.json", Epic.class);

        EpicDto res = mapper.mapDtoFromEpic(epic);

        assertEqualsToFile("/DtoMapper/EpicDto_empty.json", res);
    }

    private <T> T objectFromFile(String fileName, Class<T> objectClass) {
        String content = readFromFile(fileName);
        try {
            return objectMapper.readValue(content, objectClass);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String readFromFile(String fileName) {
        URL path = getClass().getResource(fileName);
        try {
            return Files.readString(Paths.get(path.toURI()));
        } catch (URISyntaxException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private <T> void assertEqualsToFile(String fileName, T actualObject) {
        String expected = readFromFile(fileName);
        try {
            String actual = objectMapper.writeValueAsString(actualObject);
            assertEquals(expected, actual);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static class MyDefaultPrettyPrinter extends DefaultPrettyPrinter {

        public MyDefaultPrettyPrinter() {
            this._objectIndenter = new DefaultIndenter("  ", "\n");
            this._arrayIndenter = _objectIndenter;
        }

        @Override
        public DefaultPrettyPrinter createInstance() {
            return new MyDefaultPrettyPrinter();
        }

        @Override
        public void writeObjectFieldValueSeparator(JsonGenerator g) throws IOException {
            g.writeRaw(": ");
        }
    }
}
