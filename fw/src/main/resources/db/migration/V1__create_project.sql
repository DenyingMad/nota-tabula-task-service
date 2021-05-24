CREATE TABLE PROJECT
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        VARCHAR   NOT NULL,
    description VARCHAR,
    isPersonal  BOOLEAN,
    ownerId     VARCHAR   NOT NULL,
    PRIMARY KEY (id)
);
