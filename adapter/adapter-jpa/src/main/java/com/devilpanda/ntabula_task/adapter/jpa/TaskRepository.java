package com.devilpanda.ntabula_task.adapter.jpa;

import com.devilpanda.ntabula_task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
