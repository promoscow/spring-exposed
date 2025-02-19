create table dishes
(
    id            uuid default uuid_generate_v4() primary key,
    name          varchar(255),
    price         numeric(19, 2),
    active        boolean,
    version       int,
    restaurant_id uuid references restaurants (id)
);