package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUuid(UUID uuid);

    void deleteProjectByUuid(UUID uuid);
}
