use bank;

INSERT INTO roles_type (role_name) VALUES
('Admin'),
('Customer');

INSERT INTO users (userid, username, password, role_id) VALUES
(1, 'alice@example.com', UNHEX(SHA1("password")), (SELECT role_id FROM roles_type WHERE role_name = 'Admin') ),
(2, 'raj@example.com', UNHEX(SHA1("password")), (SELECT role_id FROM roles_type WHERE role_name = 'Customer'));

INSERT INTO account_details ( userid, accno, balance, accname) VALUES
(2, '132356', 6500, 'raj santosh 1'),
(1, '993490', 800, 'raj shetty 2'),
(2, '123456', 6500, 'raj santosh'),
(2, '999990', 800, 'raj shetty');

INSERT INTO transaction_types (type_name) VALUES
('Credit'),
('Debit'),
('Transfer');

INSERT INTO `bank`.`transactions`
(`sender_accountid`,
`receiver_accountid`,
`transactionamount`,
`date`,
`type_id`) VALUES
(1, 2, 500, '2023-06-27', (SELECT `type_id` FROM `transaction_types` WHERE `type_name` = 'Transfer')),
(2, 1, 500, '2023-06-27', (SELECT `type_id` FROM `transaction_types` WHERE `type_name` = 'Transfer')),
(2, null, 200, '2024-01-15', (SELECT `type_id` FROM `transaction_types` WHERE `type_name` = 'Credit')),
(1, null, 200, '2024-01-15', (SELECT `type_id` FROM `transaction_types` WHERE `type_name` = 'Debit'));
