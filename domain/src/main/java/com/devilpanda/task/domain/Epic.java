package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "EPIC")
public class Epic extends BaseEntity {
    private UUID uuid;
    private String name;
    private String description;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectid")
    private Project project;

    @ManyToMany
    @JoinTable(name = "TEAM_EPIC",
            joinColumns = @JoinColumn(name = "epicid"),
            inverseJoinColumns = @JoinColumn(name = "teamid")
    )
    private Set<Team> teams;

    @OneToMany(mappedBy = "epic",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<TaskList> taskLists;
}
