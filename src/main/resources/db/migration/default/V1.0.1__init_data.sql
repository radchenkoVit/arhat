DROP TABLE if EXISTS users;
DROP TABLE if EXISTS roles;
DROP TABLE if EXISTS groups;
DROP TABLE if EXISTS users_groups;
DROP TABLE if EXISTS attendances;

CREATE TABLE roles (
    id IDENTITY,
    name VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT roles_PK PRIMARY KEY (id)
);

CREATE TABLE users (
    id IDENTITY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    roles_id INT NOT NULL,
    CONSTRAINT users_PK PRIMARY KEY (id),
    CONSTRAINT users_roles_FK FOREIGN KEY (roles_id) REFERENCES roles
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