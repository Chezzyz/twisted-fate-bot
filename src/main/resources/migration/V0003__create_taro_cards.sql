DROP TABLE IF EXISTS taro_cards;
CREATE TABLE taro_cards
(
    id          serial       NOT NULL,
    rus_name    varchar(255) NOT NULL,
    eng_name    varchar(255) NOT NULL,
    card_number integer      NOT NULL,
    features    text,
    description text,
    image_path  varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);