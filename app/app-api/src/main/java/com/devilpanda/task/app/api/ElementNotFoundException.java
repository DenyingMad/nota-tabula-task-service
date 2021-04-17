package com.devilpanda.task.app.api;

import java.util.UUID;

/**
 * Epic, Task, TaskList с указанным id не найден
 */
public class ElementNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Element #%s not found";

    private final String id;

    public ElementNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
        this.id = String.valueOf(id);
    }

    public ElementNotFoundException(UUID uuid) {
        super(String.format(MESSAGE, uuid));
        this.id = uuid.toString();
    }
}
