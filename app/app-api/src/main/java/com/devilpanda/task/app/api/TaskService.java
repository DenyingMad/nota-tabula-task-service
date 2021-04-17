package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskPriority;
import com.devilpanda.task.domain.TaskStatus;

import java.util.UUID;

public interface TaskService {
    Task createTask(UUID epicUuid, Long taskListId, String taskName);

    void deleteTask(UUID taskUuid);

    Task updateTaskPriority(UUID taskUuid, TaskPriority priority);

    Task updateTaskStatus(UUID taskUuid, TaskStatus status);
}
