package com.devilpanda.ntabula_task.domain;

import lombok.Getter;
import lombok.Setter;

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

    @ManyToMany
    @JoinTable(name = "MEMBER_EPIC",
            joinColumns = @JoinColumn(name = "epicid"),
            inverseJoinColumns = @JoinColumn(name = "memberid")
    )
    private Set<Member> members;

    @OneToMany(mappedBy = "epic")
    private Set<TaskList> taskLists;
}
