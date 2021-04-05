CREATE TABLE EPIC
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        VARCHAR,
    description VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE TASKLIST
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE EPIC_TASKLIST
(
    epicId     BIGINT NOT NULL,
    taskListId BIGINT NOT NULL,
    PRIMARY KEY (epicId, taskListId),
    FOREIGN KEY (epicId) REFERENCES EPIC (id),
    FOREIGN KEY (taskListId) REFERENCES TASKLIST (id)
);