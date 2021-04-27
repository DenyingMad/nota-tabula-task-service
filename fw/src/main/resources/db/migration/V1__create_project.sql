CREATE TABLE PROJECT
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        varchar   NOT NULL,
    description varchar,
    PRIMARY KEY (id)
);
