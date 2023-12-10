DROP TABLE IF EXISTS runes;
CREATE TABLE runes
(
    id              serial              NOT NULL,
    rus_name        varchar(255)        NOT NULL,
    eng_name        varchar(255)        NOT NULL,
    image_path      varchar(255)        NOT NULL,
    symbol          char                NOT NULL,
    meaning         text                NOT NULL,
    description     text                NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
)