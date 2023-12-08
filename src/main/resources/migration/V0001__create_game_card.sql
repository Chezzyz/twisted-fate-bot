DROP TABLE IF EXISTS game_cards;
CREATE TABLE game_cards
(
    id               serial      NOT NULL,
    rusName          text        NOT NULL,
    engName          text        NOT NULL,
    imagePath        text        NOT NULL,
    cardValue        text        NOT NULL,
    symbol           text        NOT NULL,
    description      text        NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);