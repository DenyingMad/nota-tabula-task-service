package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    Optional<TaskList> findByEpic_UuidAndId(UUID epicUuid, Long taskListId);

    void deleteTaskListByEpic_UuidAndId(UUID epicUuid, Long taskListId);
}
