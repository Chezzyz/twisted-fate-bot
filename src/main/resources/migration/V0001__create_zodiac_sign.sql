DROP TABLE IF EXISTS zodiac_signs;
CREATE TABLE zodiac_signs
(
    id          SERIAL,
    rus_name    VARCHAR(255) NOT NULL,
    eng_name    VARCHAR(255) NOT NULL,
    symbol      CHAR,
    from_date   VARCHAR(20)  NOT NULL,
    to_date     VARCHAR(20)  NOT NULL,
    description text,
    image_path  VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id)
);

