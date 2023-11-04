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
    transaction_type ENUM ('TOPUP', 'TRANSACTION') NOT NULL,
    description      VARCHAR(255),
    total_amount     INT                           NOT NULL,
    created_on       VARCHAR(255),
    PRIMARY KEY (invoice_number),
    CONSTRAINT FK_Transaction_User FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE banner
(
    banner_name  VARCHAR(255),
    banner_image VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    PRIMARY KEY (banner_name)
);

INSERT INTO banner
VALUES ('Banner 1', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
       ('Banner 2', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
       ('Banner 3', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
       ('Banner 4', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
       ('Banner 5', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
       ('Banner 6', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');

INSERT INTO service
VALUES ('PAJAK', 'Pajak PBB', 'https://nutech-integrasi.app/dummy.jpg', 40000),
       ('PLN', 'Listrik', 'https://nutech-integrasi.app/dummy.jpg', 10000),
       ('PDAM', 'PDAM Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 40000),
       ('PULSA', 'Pulsa', 'https://nutech-integrasi.app/dummy.jpg', 40000),
       ('PGN', 'PGN Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
       ('MUSIK', 'Musik Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
       ('TV', 'TV Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
       ('PAKET_DATA', 'Paket data', 'https://nutech-integrasi.app/dummy.jpg', 50000),
       ('VOUCHER_GAME', 'Voucher Game', 'https://nutech-integrasi.app/dummy.jpg', 100000),
       ('VOUCHER_MAKANAN', 'Voucher Makanan', 'https://nutech-integrasi.app/dummy.jpg', 100000),
       ('QURBAN', 'Qurban', 'https://nutech-integrasi.app/dummy.jpg', 200000),
       ('ZAKAT', 'Zakat', 'https://nutech-integrasi.app/dummy.jpg', 300000);
