CREATE TABLE IF NOT EXISTS taro_cards_of_the_day
(
    user_id   bigint   NOT NULL,
    card_id   smallint NOT NULL,
    timestamp timestamp,
    PRIMARY KEY (user_id),
    UNIQUE (user_id)
);