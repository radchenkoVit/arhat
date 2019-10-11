DROP TABLE if EXISTS users;
DROP TABLE if EXISTS roles;
--
--CREATE TABLE `roles` (
--  `id` INT NOT NULL AUTO_INCREMENT,
--  `name` VARCHAR(45) NOT NULL,
--  PRIMARY KEY (`id`)
--);
--
--CREATE TABLE `users` (
--  `id` INT NOT NULL AUTO_INCREMENT,
--  `name` VARCHAR(45) NOT NULL,
--  `email` VARCHAR(45) NOT NULL,
--  `password` VARCHAR(45) NOT NULL,
--  `role_id` INT NOT NULL DEFAULT 2,
--  PRIMARY KEY (`id`),
--  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
--  INDEX `role_id_FK_idx` (`role_id` ASC) VISIBLE,
--  CONSTRAINT `role_id_FK` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
--    );

create table roles (
    id bigint auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table users (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    roles_id bigint not null,
    primary key (id),
    foreign key(roles_id) references roles(id)
) engine=InnoDB;
