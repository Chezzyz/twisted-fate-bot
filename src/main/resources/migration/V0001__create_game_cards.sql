DROP TABLE IF EXISTS game_cards;
CREATE TABLE game_cards
(
    id            serial       NOT NULL,
    rus_name      varchar(255) NOT NULL,
    eng_name      varchar(255) NOT NULL,
    card_value    varchar(8)   NOT NULL,
    image_file_id varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);