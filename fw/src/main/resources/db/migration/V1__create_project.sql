CREATE TABLE PROJECT
(
    id          BIGSERIAL NOT NULL,
    uuid        UUID      NOT NULL,
    name        varchar,
    description varchar,
    PRIMARY KEY (id)
);
