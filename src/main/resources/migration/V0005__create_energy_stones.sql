DROP TABLE IF EXISTS energy_stones;
CREATE TABLE energy_stones
(
    id                   serial       NOT NULL,
    rus_name             varchar(255) NOT NULL,
    eng_name             varchar(255) NOT NULL,
    real_description     text         NOT NULL,
    esoteric_description text         NOT NULL,
    image_path           varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);