package com.devilpanda.ntabula_task.app.api;

import com.devilpanda.ntabula_task.domain.Task;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TaskService {
    Task createTask(UUID epicUuid, Long taskListId, String taskName);
}
