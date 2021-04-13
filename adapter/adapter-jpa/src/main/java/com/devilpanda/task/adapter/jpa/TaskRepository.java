package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
