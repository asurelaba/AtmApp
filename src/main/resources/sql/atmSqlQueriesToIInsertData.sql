DELETE FROM `atm_app`.`transactions`;
DELETE FROM `atm_app`.`events`;
DELETE FROM `atm_app`.`event_types`;
DELETE FROM `atm_app`.`accounts`;
DELETE FROM `atm_app`.`cards`;
DELETE FROM `atm_app`.`card_types`;
DELETE FROM `atm_app`.`users`;
DELETE FROM `atm_app`.`user_roles`;
DELETE FROM `atm_app`.`persons`;
ALTER TABLE `atm_app`.`transactions` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`events` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`event_types` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`accounts` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`cards` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`card_types` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`users` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`user_roles` AUTO_INCREMENT = 1;
ALTER TABLE `atm_app`.`persons` AUTO_INCREMENT = 1;

-- Insert card types
INSERT INTO `atm_app`.`card_types` (`name`) VALUES ('AdministratorCard'), ('ClientCard');

-- Insert user roles
INSERT INTO `atm_app`.`user_roles` (`name`) VALUES ('Administrator'), ('Client');

-- Insert persons
INSERT INTO `atm_app`.`persons` (`first_name`, `last_name`)
VALUES
('Emma', 'Wilson'),
('Liam', 'Johnson'),
('Olivia', 'Smith'),
('Noah', 'Brown'),
('Ava', 'Taylor'),
('Sophia', 'Jones'),
('Isabella', 'Miller'),
('Mia', 'Davis'),
('Charlotte', 'Anderson'),
('Amelia', 'Wilson'),
('Harper', 'Johnson'),
('Evelyn', 'Smith'),
('Abigail', 'Brown'),
('Emily', 'Taylor'),
('Elizabeth', 'Jones'),
('Mila', 'Miller'),
('Ella', 'Davis'),
('Avery', 'Anderson'),
('Sofia', 'Wilson'),
('Camila', 'Johnson'),
('Aria', 'Smith'),
('Scarlett', 'Brown'),
('Victoria', 'Taylor'),
('Madison', 'Jones'),
('Luna', 'Miller'),
('Grace', 'Davis'),
('Chloe', 'Anderson'),
('Penelope', 'Wilson'),
('Layla', 'Johnson'),
('Riley', 'Smith'),
('Zoey', 'Brown'),
('Nora', 'Taylor'),
('Lily', 'Jones'),
('Eleanor', 'Miller'),
('Hannah', 'Davis'),
('Lillian', 'Anderson'),
('Addison', 'Wilson'),
('Aubrey', 'Johnson'),
('Ellie', 'Smith'),
('Stella', 'Brown'),
('Natalie', 'Taylor'),
('Zoe', 'Jones'),
('Leah', 'Miller'),
('Hazel', 'Davis'),
('Violet', 'Anderson'),
('Aurora', 'Wilson'),
('Savannah', 'Johnson'),
('Audrey', 'Smith'),
('Brooklyn', 'Brown'),
('Bella', 'Taylor'),
('Claire', 'Jones'),
('Skylar', 'Miller'),
('Lucy', 'Davis'),
('Paisley', 'Anderson'),
('Everly', 'Wilson'),
('Anna', 'Johnson'),
('Caroline', 'Smith'),
('Nova', 'Brown'),
('Genesis', 'Taylor'),
('Emilia', 'Jones'),
('Kennedy', 'Miller'),
('Samantha', 'Davis'),
('Maya', 'Anderson'),
('Willow', 'Wilson'),
('Kinsley', 'Johnson'),
('Naomi', 'Smith'),
('Aaliyah', 'Brown'),
('Elena', 'Taylor'),
('Sarah', 'Jones'),
('Ariana', 'Miller'),
('Allison', 'Davis'),
('Gabriella', 'Anderson'),
('Alice', 'Wilson'),
('Madelyn', 'Johnson'),
('Cora', 'Smith'),
('Ruby', 'Brown'),
('Eva', 'Taylor'),
('Serenity', 'Jones'),
('Autumn', 'Miller'),
('Adeline', 'Davis'),
('Hailey', 'Anderson'),
('Gianna', 'Wilson'),
('Valentina', 'Johnson'),
('Isla', 'Smith'),
('Eliana', 'Brown'),
('Quinn', 'Taylor'),
('Nevaeh', 'Jones'),
('Ivy', 'Miller'),
('Sadie', 'Davis'),
('Piper', 'Anderson'),
('Lydia', 'Wilson'),
('Alexa', 'Johnson'),
('Josephine', 'Smith'),
('Emery', 'Brown'),
('Julia', 'Taylor'),
('Delilah', 'Jones'),
('Arianna', 'Miller'),
('Vivian', 'Davis'),
('Kaylee', 'Anderson'),
('Sophie', 'Wilson'),
('Brielle', 'Johnson'),
('Madeline', 'Smith'),
('Peyton', 'Brown'),
('Rylee', 'Taylor'),
('Clara', 'Jones'),
('Hadley', 'Miller'),
('Melanie', 'Davis'),
('Mackenzie', 'Anderson'),
('Reagan', 'Wilson'),
('Adalynn', 'Johnson'),
('Lila', 'Smith');

-- Insert users (108 clients)
INSERT INTO `atm_app`.`users` (`status`, `person_id`, `role_id`)
SELECT 'active', `person_id`, 2
FROM `atm_app`.`persons`
ORDER BY `person_id`;

-- Update users (Create 2 administrators)
UPDATE `atm_app`.`users`
SET role_id=1
WHERE user_id IN (1,2);

-- Insert cards for administrators
INSERT INTO `atm_app`.`cards` (`card_number`, `pin`, `status`, `type_id`, `user_id`)
SELECT 1111111111111110 + `person_id`, FLOOR(RAND() * (9999 - 1000 + 1)) + 1000, 'active', 1, `user_id`
FROM `atm_app`.`users`
WHERE user_id IN (1,2);

-- Insert cards for clients
INSERT INTO `atm_app`.`cards` (`card_number`, `pin`, `status`, `type_id`, `user_id`)
SELECT 2222222222222210 + `person_id`, FLOOR(RAND() * (9999 - 1000 + 1)) + 1000, 'active', 2, `user_id`
FROM `atm_app`.`users`
WHERE `user_id` BETWEEN 3 AND 110;

-- Insert accounts
INSERT INTO `atm_app`.`accounts` (`routing_number`, `balance`, `user_id`)
SELECT 123456789 + `user_id`, 10000, `user_id`
FROM `atm_app`.`users`
WHERE `role_id` = 2;

-- Insert event types
INSERT INTO `atm_app`.`event_types` (`name`)
VALUES
-- Both
('log In'),                 -- 1
('Log Out'),                -- 2
('Lock Card'),              -- 3
('Transaction Query'),      -- 4
-- User events
('Print Receipt'),          -- 5
('Change Pin'),             -- 6
('Unlock Card Request'),    -- 7
-- Users transactions
('Withdrawal'),             -- 8
('Deposit'),                -- 9
('Transfer'),               -- 10
('Balance Inquiry'),        -- 11
-- Administrator
('User Creation'),          -- 12
('Account Creation'),       -- 13
('Card Creation'),          -- 14
('Admin Creation'),         -- 15
('User Removal'),           -- 16
('Account Removal'),        -- 17
('Card Removal'),           -- 18
('Admin Removal'),          -- 19
('Unlock Card'),            -- 20
('Balance Adjustment'),     -- 21
('Accounts Query'),          -- 22
('Users Query'),             -- 23
('Cards Query'),             -- 24
('Events Query'),            -- 25
('Transactions Query');      -- 26

-- Insert events for administrators
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (4 - 1 + 1)) + 1
FROM `atm_app`.`cards`
WHERE `user_id` IN (1,2);

INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (26 - 12 + 1)) + 12
FROM `atm_app`.`cards`
WHERE `user_id` IN (1,2);

-- Insert events for clients
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (11 - 1 + 1)) + 1
FROM `atm_app`.`cards`
WHERE `user_id` BETWEEN 3 AND 110;

-- Insert more events only for transactions
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (11 - 8 + 1)) + 8
FROM `atm_app`.`cards`
WHERE `user_id` BETWEEN 3 AND 110;

-- Insert transactions
INSERT INTO `atm_app`.`transactions` (`amount`, `status`, `event_id`)
SELECT 100, 'approved', `event_id`
FROM `atm_app`.`events`
WHERE `type_id` IN (8, 9, 10, 11);

-- Update admin and user for demo
UPDATE `atm_app`.`cards`
SET pin = 8051
WHERE card_number = 1111111111111111;
UPDATE `atm_app`.`cards`
SET pin = 8303
WHERE card_number = 2222222222222213;