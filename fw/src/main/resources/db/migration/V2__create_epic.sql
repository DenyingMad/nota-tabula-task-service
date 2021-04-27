CREATE TABLE EPIC
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        VARCHAR   NOT NULL,
    description VARCHAR,
    projectId   BIGINT    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (projectId) REFERENCES PROJECT (id)
);
