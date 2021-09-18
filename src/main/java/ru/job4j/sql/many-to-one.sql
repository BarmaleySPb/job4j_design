create table palette(
    id serial primary key,
    name varchar(255)
);

create table colors(
    id serial primary key,
    name varchar(255),
    palette_id int references palette(id)
);

insert into palette(name) values ('firstPalette');
insert into colors(name, palette_id) VALUES ('blue', 1);
insert into colors(name, palette_id) VALUES ('red', 1);
insert into colors(name, palette_id) VALUES ('yellow', 1);
insert into colors(name, palette_id) VALUES ('green', 1);

select * from colors;

select * from palette where id in (select id from colors);