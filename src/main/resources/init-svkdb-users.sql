CREATE TABLE IF NOT EXISTS USERS (
    id int primary key,
    name VARCHAR(100),
    surname VARCHAR(100),
    email VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL
);
INSERT INTO USERS (id, name, surname, email, address) VALUES (1, 'svk1', 'rusl1', 'svk1@mail.ru', 'svk1Address');
INSERT INTO USERS (id, name, surname, email, address) VALUES (2, 'svk2', 'rusl2', 'svk2@mail.ru', 'svk2Address');
INSERT INTO USERS (id, name, surname, email, address) VALUES (3, 'svk3', 'rusl3', 'svk3@mail.ru', 'svk3Address');
INSERT INTO USERS (id, name, surname, email, address) VALUES (4, 'svk4', 'rusl4', 'svk4@mail.ru', 'svk4Address');