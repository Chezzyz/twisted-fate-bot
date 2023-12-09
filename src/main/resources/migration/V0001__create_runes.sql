DROP TABLE IF EXISTS runes;
CREATE TABLE runes
(
    id              serial      NOT NULL,
    rus_name        text        NOT NULL,
    eng_name        text        NOT NULL,
    image_path      text        NOT NULL,
    symbol          text        NOT NULL,
    meaning         text        NOT NULL,
    description      text       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
)