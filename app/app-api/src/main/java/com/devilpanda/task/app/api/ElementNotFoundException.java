package com.devilpanda.task.app.api;

import java.util.UUID;

/**
 * Epic, Task, TaskList с указанными uuid и/или id не найден(ы)
 */
public class ElementNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Element #%s not found";
    private static final String MESSAGE_TASK_LIST_NOT_FOUND = "Element with UUID #%s, and ID #%s not found";

    public ElementNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }

    public ElementNotFoundException(UUID uuid) {
        super(String.format(MESSAGE, uuid));
    }

    public ElementNotFoundException(UUID uuid, Long id) {
        super(String.format(MESSAGE_TASK_LIST_NOT_FOUND, uuid, id));
    }
}
