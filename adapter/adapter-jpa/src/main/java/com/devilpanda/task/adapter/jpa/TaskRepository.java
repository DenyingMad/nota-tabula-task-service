package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteTaskByUuid(UUID uuid);
}
