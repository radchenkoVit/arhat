DROP TABLE if EXISTS users;

CREATE TABLE users (
    id IDENTITY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    activation_code VARCHAR(100),
    active BOOLEAN DEFAULT FALSE,
    role VARCHAR(100) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    CONSTRAINT users_PK PRIMARY KEY (id)
    );

--CREATE TABLE groups (
--    id IDENTITY,
--    name VARCHAR(100) NOT NULL UNIQUE,
--    CONSTRAINT groups_PK PRIMARY KEY (id)
--);
--
--CREATE TABLE users_groups (
--    users_id INT,
--    groups_id INT,
--    CONSTRAINT users_groups_groups_FK FOREIGN KEY (groups_id) REFERENCES groups,
--    CONSTRAINT users_groups_users_FK FOREIGN KEY (users_id) REFERENCES users,
--    CONSTRAINT users_groups_PK PRIMARY KEY (groups_id, users_id)
--);
--
--CREATE TABLE attendances (
--    id IDENTITY,
--    users_id INT,
--    groups_id INT,
--    CONSTRAINT attendances_users_FK FOREIGN KEY (users_id) REFERENCES users,
--    CONSTRAINT attendances_groups_FK FOREIGN KEY (groups_id) REFERENCES groups,
--    CONSTRAINT attendances_PK PRIMARY KEY (id)
--);