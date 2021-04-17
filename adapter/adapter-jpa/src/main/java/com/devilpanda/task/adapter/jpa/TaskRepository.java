package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUuid(UUID uuid);

    void deleteTaskByUuid(UUID uuid);
}
