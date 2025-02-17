create table orders
(
    id            uuid default uuid_generate_v4() primary key,
    date          date,
    user_id       uuid references users (id),
    dish_id       uuid references dishes (id),
    constraint ux_date_user unique (date, user_id)
);