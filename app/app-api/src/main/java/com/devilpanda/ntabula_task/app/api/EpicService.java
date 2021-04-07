package com.devilpanda.ntabula_task.app.api;

import com.devilpanda.ntabula_task.domain.Epic;
import com.devilpanda.ntabula_task.domain.TaskList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EpicService {
    Epic createEpic();
    TaskList createTaskList(UUID epicUuid, String taskListName);
    List<Epic> getAllEpics();
}
