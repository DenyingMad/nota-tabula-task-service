package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "TEAM")
public class Team extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "team")
    private Set<TeamMember> members;
}
