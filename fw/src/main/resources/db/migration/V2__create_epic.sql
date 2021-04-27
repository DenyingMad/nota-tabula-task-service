CREATE TABLE EPIC
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    projectId   BIGINT    NOT NULL,
    name        VARCHAR,
    description VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (projectId) REFERENCES PROJECT (id)
);
