package com.devilpanda.task.app.api;

import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.TaskList;

import java.util.List;
import java.util.UUID;

public interface EpicService {
    Epic createEpic();

    TaskList createTaskList(UUID epicUuid, String taskListName);

    List<Epic> getAllEpics();

    void deleteEpicByUuid(UUID epicUuid);

    void deleteTaskList(UUID epicUuid, Long taskListId);

    Epic renameEpic(UUID epicUuid, String name);
}
