DROP TABLE if EXISTS users;

create table users (
    id bigint not null auto_increment,
     activation_code varchar(100),
     active boolean not null default 0,
     email varchar(100) not null,
     name varchar(100) not null,
     password varchar(100) not null,
     role varchar(100) not null,
     primary key (id)
 ) engine=InnoDB;


--CREATE TABLE users (
--        id BIGINT NOT NULL AUTO_INCREMENT,
--        name VARCHAR(100) NOT NULL,
--        email VARCHAR(100) NOT NULL,
--        password VARCHAR(100) NOT NULL,
--        active false,
--        role VARCHAR(100) NOT NULL,
--        CONSTRAINT PK_id PRIMARY KEY (id)
--)

--create table users (
--    id bigint not null auto_increment,
--    name varchar(100) not null,
--    email varchar(100) not null,
--    password varchar(100) not null,
--    role varchar(100) not null,
--    primary key (id)
--) engine=InnoDB;
