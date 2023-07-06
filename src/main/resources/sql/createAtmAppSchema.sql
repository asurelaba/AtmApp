-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema atm_app
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `atm_app`;

-- -----------------------------------------------------
-- Schema atm_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atm_app` DEFAULT CHARACTER SET utf8;
-- -----------------------------------------------------
-- Schema atm_app
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `atm_app`;

-- -----------------------------------------------------
-- Schema atm_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atm_app` DEFAULT CHARACTER SET utf8;
USE `atm_app`;
USE `atm_app`;

-- -----------------------------------------------------
-- Table `atm_app`.`persons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`persons`
(
    `person_id`  INT(11)     NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`person_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 112
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`user_roles`
(
    `role_id` INT(11)     NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`users`
(
    `user_id`   INT(11)     NOT NULL AUTO_INCREMENT,
    `status`    VARCHAR(45) NOT NULL,
    `person_id` INT(11)     NOT NULL,
    `role_id`   INT(11)     NOT NULL,
    PRIMARY KEY (`user_id`),
    INDEX `fk_users_user_roles1_idx` (`role_id` ASC),
    INDEX `fk_users_persons1_idx` (`person_id` ASC),
    CONSTRAINT `fk_users_persons1`
        FOREIGN KEY (`person_id`)
            REFERENCES `atm_app`.`persons` (`person_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_users_user_roles1`
        FOREIGN KEY (`role_id`)
            REFERENCES `atm_app`.`user_roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 128
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`accounts`
(
    `account_id`     INT(11)        NOT NULL AUTO_INCREMENT,
    `routing_number` INT(11)        NOT NULL,
    `balance`        DECIMAL(10, 0) NOT NULL,
    `user_id`        INT(11)        NOT NULL,
    PRIMARY KEY (`account_id`),
    INDEX `fk_accounts_users1_idx` (`user_id` ASC),
    CONSTRAINT `fk_accounts_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `atm_app`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 128
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`card_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`card_types`
(
    `type_id` INT(11)     NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`type_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`cards`
(
    `card_id`     INT(11)     NOT NULL AUTO_INCREMENT,
    `card_number` BIGINT(16)  NOT NULL,
    `pin`         INT(11)     NOT NULL,
    `status`      VARCHAR(45) NOT NULL,
    `type_id`     INT(11)     NOT NULL,
    `user_id`     INT(11)     NOT NULL,
    PRIMARY KEY (`card_id`),
    INDEX `fk_cards_card_types1_idx` (`type_id` ASC),
    INDEX `fk_cards_users1` (`user_id` ASC),
    CONSTRAINT `fk_cards_card_types1`
        FOREIGN KEY (`type_id`)
            REFERENCES `atm_app`.`card_types` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_cards_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `atm_app`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 131
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`event_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`event_types`
(
    `type_id` INT(11)     NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`type_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 22
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`events`
(
    `event_id` INT(11)   NOT NULL AUTO_INCREMENT,
    `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `card_id`  INT(11)   NOT NULL,
    `type_id`  INT(11)   NOT NULL,
    PRIMARY KEY (`event_id`),
    INDEX `fk_events_cards1_idx` (`card_id` ASC),
    INDEX `fk_events_event_transaction_types1_idx` (`type_id` ASC),
    CONSTRAINT `fk_events_cards1`
        FOREIGN KEY (`card_id`)
            REFERENCES `atm_app`.`cards` (`card_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_events_event_transaction_types1`
        FOREIGN KEY (`type_id`)
            REFERENCES `atm_app`.`event_types` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 261
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `atm_app`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_app`.`transactions`
(
    `transaction_id` INT(11)        NOT NULL AUTO_INCREMENT,
    `amount`         DECIMAL(10, 0) NOT NULL,
    `status`         VARCHAR(45)    NOT NULL,
    `event_id`       INT(11)        NOT NULL,
    PRIMARY KEY (`transaction_id`),
    INDEX `fk_financial_transactions_events1_idx` (`event_id` ASC),
    CONSTRAINT `fk_financial_transactions_events1`
        FOREIGN KEY (`event_id`)
            REFERENCES `atm_app`.`events` (`event_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 32
    DEFAULT CHARACTER SET = utf8;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
