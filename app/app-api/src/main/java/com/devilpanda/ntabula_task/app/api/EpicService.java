package com.devilpanda.ntabula_task.app.api;

import com.devilpanda.ntabula_task.domain.Epic;
import org.springframework.stereotype.Service;

@Service
public interface EpicService {
    Epic createEpic();
}
