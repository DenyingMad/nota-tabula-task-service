package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.UUID;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {
    private UUID uuid;
    private String name;
    private String description;

    @OneToMany(mappedBy = "project",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Epic> epics;
}
