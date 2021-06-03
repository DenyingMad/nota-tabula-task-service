package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.app.api.ElementNotFoundException;
import com.devilpanda.task.app.api.InvalidTaskStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ElementNotFoundException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTaskStatusException.class)
    public ResponseEntity<Object> handleInvalidTaskStatusException(InvalidTaskStatusException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error(e.getMessage());
        LOGGER.trace(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
