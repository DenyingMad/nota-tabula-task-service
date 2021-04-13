package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {
    private String login;
    private String role;

    @ManyToMany(mappedBy = "members")
    private Set<Epic> epics;
}
