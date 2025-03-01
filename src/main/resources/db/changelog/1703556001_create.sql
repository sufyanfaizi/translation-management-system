--liquibase formatted sql

--changeset sufyan.faizi:1
CREATE TABLE translations (
                              translation_id bigint NOT NULL AUTO_INCREMENT,
                              locale VARCHAR(10) NOT NULL,
                              translation_key VARCHAR(255) NOT NULL,
                              tags VARCHAR(255) NOT NULL,
                              content VARCHAR(255) NOT NULL,
                              `created_by`              bigint DEFAULT NULL,
                              `created_at`              timestamp NOT NULL,
                              `updated_by`              bigint DEFAULT NULL,
                              `updated_at`              timestamp NULL DEFAULT NULL,
                              PRIMARY KEY (`translation_id`)

);

--changeset sufyan.faizi:2
CREATE INDEX idx_locale ON translations(locale);

--changeset sufyan.faizi:3
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);



