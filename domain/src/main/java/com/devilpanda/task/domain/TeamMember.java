package com.devilpanda.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @Enumerated(EnumType.STRING)
    private TeamRole role;
}
