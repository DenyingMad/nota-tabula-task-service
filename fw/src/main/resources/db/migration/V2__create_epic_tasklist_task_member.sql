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
    id     BIGSERIAL NOT NULL,
    epicId BIGINT    NOT NULL,
    name   VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (epicId) REFERENCES EPIC (id)
);

CREATE TABLE TASK
(
    id          BIGSERIAL NOT NULL,
    taskListId  BIGINT,
    uuid        UUID      NOT NULL,
    name        VARCHAR   NOT NULL,
    description VARCHAR,
    priority    INT,
    checked     BOOLEAN   NOT NULL DEFAULT FALSE,
    assigned    BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (taskListId) REFERENCES TASKLIST (id)
);

-- Временное решение
CREATE TABLE MEMBER
(
    id    BIGSERIAL NOT NULL,
    login VARCHAR   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE MEMBER_EPIC
(
    memberId BIGINT  NOT NULL,
    epicId   BIGINT  NOT NULL,
    role     VARCHAR NOT NULL,
    PRIMARY KEY (memberId, epicId),
    FOREIGN KEY (memberId) REFERENCES MEMBER (id),
    FOREIGN KEY (epicId) REFERENCES EPIC (id)
);