# Diagram https://dbdiagram.io/d/ppob-654324c87d8bbd646551e231

CREATE DATABASE ppob;

USE ppob;

CREATE TABLE user
(
    email         VARCHAR(255),
    password      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    profile_image VARCHAR(255),
    balance       INT          NOT NULL DEFAULT 0,
    PRIMARY KEY (email)
);

CREATE TABLE service
(
    service_code   VARCHAR(255),
    service_name   VARCHAR(255) NOT NULL,
    service_icon   VARCHAR(255),
    service_tariff INT          NOT NULL,
    PRIMARY KEY (service_code)
);

CREATE TABLE transaction
(
    invoice_number   VARCHAR(255),
    user_email       VARCHAR(255)                  NOT NULL,
    service_code     VARCHAR(255)                  NOT NULL,
    transaction_type ENUM ('TOPUP', 'TRANSACTION') NOT NULL,
    description      VARCHAR(255),
    total_amount     INT                           NOT NULL,
    created_on       VARCHAR(255),
    PRIMARY KEY (invoice_number),
    CONSTRAINT FK_Transaction_User FOREIGN KEY (user_email) REFERENCES user (email),
    CONSTRAINT FK_Transaction_Service FOREIGN KEY (service_code) REFERENCES service (service_code)
);

CREATE TABLE banner
(
    banner_name  VARCHAR(255),
    banner_image VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    PRIMARY KEY (banner_name)
)
