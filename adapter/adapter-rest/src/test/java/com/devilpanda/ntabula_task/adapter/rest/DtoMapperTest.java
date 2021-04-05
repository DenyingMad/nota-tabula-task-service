package com.devilpanda.ntabula_task.adapter.rest;

import com.devilpanda.ntabula_task.domain.Epic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoMapperTest {

    private DtoMapper mapper;
    private ObjectMapper objectMapper;

    @Test
    public void mapDtoFromEpic() {
        Epic epic = objectFromFile("/", Epic.class);

        EpicDto res = mapper.mapDtoFromEpic(epic);

        assertFilesEquals("/", res);
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

    private <T> void assertFilesEquals(String fileName, T actualObject) {
        String expected = readFromFile(fileName);
        try {
            String actual = objectMapper.writeValueAsString(actualObject);
            assertEquals(expected, actual);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
