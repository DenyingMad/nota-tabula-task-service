package com.devilpanda.ntabula_task.adapter.jpa;

import com.devilpanda.ntabula_task.domain.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EpicRepository extends JpaRepository<Epic, Long> {

    Optional<Epic> findByUuid(UUID uuid);
}
