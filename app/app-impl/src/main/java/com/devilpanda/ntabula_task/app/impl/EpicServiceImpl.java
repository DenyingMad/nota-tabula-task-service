package com.devilpanda.ntabula_task.app.impl;

import com.devilpanda.ntabula_task.adapter.jpa.EpicRepository;
import com.devilpanda.ntabula_task.app.api.EpicService;
import com.devilpanda.ntabula_task.domain.Epic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicRepository epicRepository;

    @Override
    public Epic createEpic() {
        Epic epic = new Epic();
        epic.setUuid(UUID.randomUUID());
        return epicRepository.saveAndFlush(epic);
    }

    @Override
    public List<Epic> getAllEpics() {
        // todo поиск эпиков в которых текущий пользователь является участником
        return epicRepository.findAll();
    }
}
