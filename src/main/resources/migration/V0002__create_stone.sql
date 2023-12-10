DROP TABLE IF EXISTS energy_stone;
CREATE TABLE energy_stone
(
    id                   SERIAL,
    rus_name             VARCHAR(255) NOT NULL,
    eng_name             VARCHAR(255) NOT NULL,
    esoteric_description text         NOT NULL,
    real_description     text         NOT NULL,
    image_path           VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id)
);