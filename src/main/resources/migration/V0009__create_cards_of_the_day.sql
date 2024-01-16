CREATE TABLE IF NOT EXISTS taro_cards_of_the_day
(
    user_id   bigint   NOT NULL,
    card_id   smallint NOT NULL,
    updated_timestamp timestamptz,
    PRIMARY KEY (user_id),
    UNIQUE (user_id)
);
CREATE UNIQUE INDEX IF NOT EXISTS card_user_id_udx ON taro_cards_of_the_day using btree(user_id);