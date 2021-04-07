package com.devilpanda.ntabula_task.app.api;

import com.devilpanda.ntabula_task.domain.Epic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EpicService {
    Epic createEpic();
    List<Epic> getAllEpics();
}
