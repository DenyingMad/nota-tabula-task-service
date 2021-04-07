package com.devilpanda.ntabula_task.app.impl;

import com.devilpanda.ntabula_task.adapter.jpa.TaskListRepository;
import com.devilpanda.ntabula_task.adapter.jpa.TaskRepository;
import com.devilpanda.ntabula_task.app.api.TaskService;
import com.devilpanda.ntabula_task.domain.Task;
import com.devilpanda.ntabula_task.domain.TaskList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public Task createTask(UUID epicUuid, Long taskListId, String taskName) {
        TaskList taskList = taskListRepository.findByEpic_UuidAndId(epicUuid, taskListId)
                .orElseThrow(EntityNotFoundException::new);

        Task task = new Task();
        task.setUuid(UUID.randomUUID());
        task.setName(taskName);
        task.setTaskList(taskList);
        task.setChecked(false);

        return taskRepository.saveAndFlush(task);
    }
}
