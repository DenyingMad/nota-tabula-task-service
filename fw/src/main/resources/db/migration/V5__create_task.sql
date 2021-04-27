CREATE TABLE TASK
(
    id          BIGSERIAL NOT NULL,
    taskListId  BIGINT,
    uuid        UUID      NOT NULL,
    name        VARCHAR   NOT NULL,
    description VARCHAR,
    priority    VARCHAR,
    status      VARCHAR   NOT NULL,
    assigned    BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (taskListId) REFERENCES TASKLIST (id),
    FOREIGN KEY (assigned) REFERENCES MEMBER (id)
);
