create table plate_numbers(
    id serial primary key,
    number varchar(255)
);

create table vins(
    id serial primary key,
    number varchar(255),
    plate_numbers_id int references plate_numbers(id) unique
);