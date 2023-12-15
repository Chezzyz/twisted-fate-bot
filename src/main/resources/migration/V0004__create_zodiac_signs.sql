DROP TABLE IF EXISTS zodiac_signs;
CREATE TABLE zodiac_signs
(
    id            serial       NOT NULL,
    rus_name      varchar(255) NOT NULL,
    eng_name      varchar(255) NOT NULL,
    symbol        char         NOT NULL,
    start_date    varchar(20)  NOT NULL,
    end_date      varchar(20)  NOT NULL,
    description   text,
    image_file_id varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);

