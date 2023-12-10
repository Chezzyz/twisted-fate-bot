DROP TABLE IF EXISTS game_cards;
CREATE TABLE game_cards
(
    id               serial      NOT NULL,
    rus_name          text        NOT NULL,
    eng_name          text        NOT NULL,
    image_path        text        NOT NULL,
    card_value        text        NOT NULL,
    description      text        NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);