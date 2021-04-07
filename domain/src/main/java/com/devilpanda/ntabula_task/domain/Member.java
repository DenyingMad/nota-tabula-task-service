package com.devilpanda.ntabula_task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {
    private String login;
    private String role;
}
