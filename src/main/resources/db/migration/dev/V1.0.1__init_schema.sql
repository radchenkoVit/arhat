DROP TABLE if EXISTS users;

create table users (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    activation_code VARCHAR(100),
    active Boolean DEFAULT FALSE,
    role varchar(100) not null,
    created_date TIMESTAMP NOT NULL,
    primary key (id)
) engine=InnoDB;
