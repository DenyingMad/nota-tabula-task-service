package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.TaskStatus;

import java.util.UUID;

public class InvalidTaskStatusException extends RuntimeException {
    private static final String MESSAGE = "Can not set status \"%s\" for task with uuid: %s";

    private final String id;
    private final TaskStatus taskStatus;

    public InvalidTaskStatusException(UUID taskUuid, TaskStatus taskStatus) {
        super(String.format(MESSAGE, taskStatus, taskUuid));
        this.id = taskUuid.toString();
        this.taskStatus = taskStatus;
    }
}
