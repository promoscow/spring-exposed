create table restaurants
(
    id   uuid default uuid_generate_v4() primary key,
    name varchar(255)
);