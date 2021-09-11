CREATE DATABASE IF NOT EXISTS account;

USE account;

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    account_id bigint NOT NULL,
    consumer_id bigint NOT NULL,
    order_id bigint,
    payment_amount bigint NOT NULL,
    PRIMARY KEY (account_id)
);