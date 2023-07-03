-- Reset
DELETE FROM transactions;
DELETE FROM events;
DELETE FROM event_types;
DELETE FROM accounts;
DELETE FROM cards;
DELETE FROM card_types;
DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM persons;
ALTER TABLE transactions AUTO_INCREMENT = 1;
ALTER TABLE events AUTO_INCREMENT = 1;
ALTER TABLE event_types AUTO_INCREMENT = 1;
ALTER TABLE accounts AUTO_INCREMENT = 1;
ALTER TABLE cards AUTO_INCREMENT = 1;
ALTER TABLE card_types AUTO_INCREMENT = 1;
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE user_roles AUTO_INCREMENT = 1;
ALTER TABLE persons AUTO_INCREMENT = 1;

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
('log In'), 
('Log Out'),
('Lock Card'), 
('Transaction Query'),
-- User events
('Check Balance'),
('Print Receipt'),
('Change Pin'),
('Unlock Card Request'), 
-- Users transactions
('Withdrawal'),
('Deposit'),
('Transfer'),
-- Administrator
('User Creation'),
('Account Creation'),
('Card Creation'),
('Admin Creation'),
('User Removal'),
('Account Removal'),
('Card Removal'),
('Admin Removal'),
('Unlock Card'),
('Balance Adjustment');

-- Insert events for administrators
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (4 - 1 + 1)) + 1
FROM `atm_app`.`cards`
WHERE `user_id` IN (1,2);

INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (21 - 12 + 1)) + 12
FROM `atm_app`.`cards`
WHERE `user_id` IN (1,2);

-- Insert events for clients
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (8 - 1 + 1)) + 1
FROM `atm_app`.`cards`
WHERE `user_id` BETWEEN 3 AND 110;

-- Insert events for transactions
INSERT INTO `atm_app`.`events` (`datetime`, `card_id`, `type_id`)
SELECT NOW(), `card_id`, FLOOR(RAND() * (11 - 9 + 1)) + 9
FROM `atm_app`.`cards`
WHERE `user_id` BETWEEN 3 AND 110;

-- Insert transactions
INSERT INTO `atm_app`.`transactions` (`amount`, `status`, `event_id`)
SELECT 100, 'approved', `event_id`
FROM `atm_app`.`events`
WHERE `type_id` = FLOOR(RAND() * (11 - 9 + 1)) + 9;

-- See all users
SELECT * FROM users
JOIN persons ON persons.person_id=users.person_id
JOIN user_roles ON user_roles.role_id=users.role_id
JOIN cards ON cards.user_id=users.person_id
JOIN card_types ON card_types.type_id=cards.type_id;

-- See all accounts (users one and two are admin, so expected values in account is NULL)
SELECT * FROM accounts
RIGHT JOIN users ON accounts.user_id=users.user_id
JOIN persons ON persons.person_id= users.person_id;

-- See all Activity (events)
SELECT * FROM events
LEFT JOIN transactions ON transactions.event_id=events.event_id
JOIN event_types ON event_types.type_id=events.type_id
ORDER BY events.event_id;

-- See only the transactions activity (from events)
SELECT * FROM events
JOIN transactions ON transactions.event_id=events.event_id
JOIN event_types ON event_types.type_id=events.type_id
ORDER BY events.event_id;

-- Transactions
SELECT count(transaction_id) FROM transactions;