package com.devilpanda.task.app.impl;

import com.devilpanda.task.adapter.jpa.EpicRepository;
import com.devilpanda.task.adapter.jpa.TaskListRepository;
import com.devilpanda.task.app.api.ElementNotFoundException;
import com.devilpanda.task.app.api.EpicService;
import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.TaskList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicRepository epicRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public Epic createEpic() {
        Epic epic = new Epic();
        epic.setUuid(UUID.randomUUID());

        epic = epicRepository.saveAndFlush(epic);
        epic.setTaskLists(Stream.of(createTaskList(epic.getUuid(), "Default TaskList")).collect(toSet()));

        return epic;
    }

    @Override
    public TaskList createTaskList(UUID epicUuid, String taskListName) {
        Epic epic = epicRepository.findByUuid(epicUuid)
                .orElseThrow(EntityNotFoundException::new);

        TaskList taskList = new TaskList();
        taskList.setName(taskListName);
        taskList.setEpic(epic);

        return taskListRepository.saveAndFlush(taskList);
    }

    @Override
    public List<Epic> getAllEpics() {
        // todo поиск эпиков в которых текущий пользователь является участником
        return epicRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteEpicByUuid(UUID epicUuid) {
        epicRepository.deleteEpicByUuid(epicUuid);
    }

    @Transactional
    @Override
    public void deleteTaskList(UUID epicUuid, Long taskListId) {
        taskListRepository.deleteTaskListByEpic_UuidAndId(epicUuid, taskListId);
    }

    @Override
    public Epic renameEpic(UUID epicUuid, String name) {
        Epic epic = epicRepository.findByUuid(epicUuid)
                .orElseThrow(() -> new ElementNotFoundException(epicUuid));

        epic.setName(name);

        return epicRepository.saveAndFlush(epic);
    }
}
