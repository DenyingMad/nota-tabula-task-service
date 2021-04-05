CREATE TABLE TASK
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        VARCHAR   NOT NULL,
    description VARCHAR,
    priority    INT,
    taskListId  BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (taskListId) REFERENCES TASKLIST (id)
);