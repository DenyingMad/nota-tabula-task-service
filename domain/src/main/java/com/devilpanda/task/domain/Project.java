package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "ownerid")
    private String ownerId;
    @Column(name = "ispersonal")
    private Boolean isPersonal;

    @OneToMany(mappedBy = "project",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Epic> epics;
}
