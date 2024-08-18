CREATE DATABASE bank;
use bank;

CREATE TABLE roles_type (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(10) UNIQUE
);

CREATE TABLE users (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password varbinary(64) NOT NULL,
    role_id INT NOT NULL,
	FOREIGN KEY (role_id) REFERENCES roles_type(role_id)
);

CREATE TABLE account_details (
    acc_id INT PRIMARY KEY AUTO_INCREMENT,
    userid INT NOT NULL,
    accno VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    accname VARCHAR(255) NOT NULL,
    isactive BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (userid) REFERENCES users(userid)
);

CREATE TABLE transaction_types (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(10) UNIQUE
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_accountid INT NOT NULL,
    receiver_accountid INT,
    transactionamount DECIMAL(10, 2) NOT NULL,
    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    type_id INT NOT NULL,
    FOREIGN KEY (sender_accountid) REFERENCES account_details(acc_id),
	FOREIGN KEY (receiver_accountid) REFERENCES account_details(acc_id),
    FOREIGN KEY (type_id) REFERENCES transaction_types(type_id)
);

