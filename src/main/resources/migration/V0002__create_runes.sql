DROP TABLE IF EXISTS runes;
CREATE TABLE runes
(
    id            serial       NOT NULL,
    rus_name      varchar(255) NOT NULL,
    eng_name      varchar(255) NOT NULL,
    symbol        char         NOT NULL,
    translation   text         NOT NULL,
    description   text,
    image_file_id varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);