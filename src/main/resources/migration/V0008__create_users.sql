CREATE TABLE IF NOT EXISTS users
(
    tg_user_id bigint NOT NULL,
    first_name varchar(255),
    last_name  varchar(255),
    user_name  varchar(255),
    state      smallint,
    PRIMARY KEY (tg_user_id),
    UNIQUE (tg_user_id)
);
CREATE UNIQUE INDEX IF NOT EXISTS user_id_udx ON users using btree (tg_user_id);