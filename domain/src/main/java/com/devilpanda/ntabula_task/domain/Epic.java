package com.devilpanda.ntabula_task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Epic extends BaseEntity {
    private UUID uuid;
    private String name;
    private String description;
}
