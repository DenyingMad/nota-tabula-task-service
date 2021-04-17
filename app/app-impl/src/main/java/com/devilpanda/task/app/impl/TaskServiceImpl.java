package com.devilpanda.task.app.impl;

import com.devilpanda.task.adapter.jpa.TaskListRepository;
import com.devilpanda.task.adapter.jpa.TaskRepository;
import com.devilpanda.task.app.api.ElementNotFoundException;
import com.devilpanda.task.app.api.TaskService;
import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskList;
import com.devilpanda.task.domain.TaskPriority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static com.devilpanda.task.domain.TaskPriority.MEDIUM;

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
        task.setPriority(MEDIUM);

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task updateTaskPriority(UUID taskUuid, TaskPriority priority) {
        Task task = taskRepository.findByUuid(taskUuid)
                .orElseThrow(() -> new ElementNotFoundException(taskUuid));

        if (priority.equals(task.getPriority()))
            return task;
        task.setPriority(priority);

        return taskRepository.saveAndFlush(task);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskUuid) {
         taskRepository.deleteTaskByUuid(taskUuid);
    }
}
