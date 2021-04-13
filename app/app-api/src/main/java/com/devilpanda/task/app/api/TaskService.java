package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.Task;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TaskService {
    Task createTask(UUID epicUuid, Long taskListId, String taskName);
}
