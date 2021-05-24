package com.devilpanda.task.adapter.jpa;

import com.devilpanda.task.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUuid(UUID uuid);

    @Query("SELECT p FROM Project p WHERE p.isPersonal = :isPersonal AND p.ownerId = :ownerId")
    List<Project> findProjectsByOwner(String ownerId, Boolean isPersonal);
}
