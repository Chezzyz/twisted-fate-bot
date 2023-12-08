DROP TABLE IF EXISTS stone_energy;
CREATE TABLE stone_energy
(
    id                   SERIAL ,
    rus_name             text NOT NULL,
    eng_name             text NOT NULL,
    image_path           text NOT NULL,
    esoteric_description VARCHAR(255) NOT NULL,
    real_description     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);