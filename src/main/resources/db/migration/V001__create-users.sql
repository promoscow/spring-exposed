create extension if not exists "uuid-ossp";

create table users
(
    id       uuid default uuid_generate_v4() primary key,
    username varchar(255) not null,
    password varchar(255) not null
);