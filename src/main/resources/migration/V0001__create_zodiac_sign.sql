DROP TABLE IF EXISTS zodiac_signs;
CREATE TABLE zodiac_signs
(
    id          SERIAL,
    eng_name    VARCHAR(255) NOT NULL,
    rus_name    VARCHAR(255) NOT NULL,
    from_date   VARCHAR(20)  NOT NULL,
    to_date     VARCHAR(20)  NOT NULL,
    symbol      CHAR,
    description text,
    image_path  VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id)
)

