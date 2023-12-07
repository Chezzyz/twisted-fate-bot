CREATE TABLE IF NOT EXISTS taro_cards
(
    id          SERIAL,
    rus_name    VARCHAR(255),
    eng_name    VARCHAR(255),
    image_path  VARCHAR(255),
    card_number INTEGER                                  NOT NULL,
    description TEXT,
    features TEXT,
    PRIMARY KEY (id),
    UNIQUE (id)
);