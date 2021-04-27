CREATE TABLE TEAM
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE TEAM_EPIC
(
    teamId BIGINT NOT NULL,
    epicId BIGINT NOT NULL,
    PRIMARY KEY (teamId, epicId),
    FOREIGN KEY (teamId) REFERENCES TEAM (id),
    FOREIGN KEY (epicId) REFERENCES EPIC (id)
);

CREATE TABLE MEMBER
(
    id    BIGSERIAL NOT NULL,
    login VARCHAR   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE TEAM_MEMBER
(
    teamId   BIGINT NOT NULL,
    memberId BIGINT NOT NULL,
    role     VARCHAR,
    PRIMARY KEY (teamId, memberId),
    FOREIGN KEY (teamId) REFERENCES TEAM (id),
    FOREIGN KEY (memberId) REFERENCES MEMBER (id)
);
