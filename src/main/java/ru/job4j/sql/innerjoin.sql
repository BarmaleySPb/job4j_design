create table address(
    id serial primary key,
    street varchar(255),
    build int,
	appartment int
);

create table people(
    id serial primary key,
    name varchar(255),
    address_id int references address(id)
);

insert into address(street, build, appartment) values('first', 2, 15);
insert into address(street, build, appartment) values('first', 2, 17);
insert into address(street, build, appartment) values('first', 2, 18);
insert into address(street, build, appartment) values('first', 2, 19);
insert into address(street, build, appartment) values('second', 1, 15);

insert into people(name, address_id) values ('Vasya', 1);
insert into people(name, address_id) values ('Anya', 1);
insert into people(name, address_id) values ('Klava', 2);
insert into people(name, address_id) values ('Petya', 3);
insert into people(name, address_id) values ('Olya', 4);
insert into people(name, address_id) values ('Ira', 5);
insert into people(name, address_id) values ('Oleg', 5);
insert into people(name, address_id) values ('Igor', 5);

select * from people join address ad on people.address_id = ad.id;
select p.name as Имя, ad.street as Улица 
	from people as p join address as ad on p.address_id = ad.id;
select p.name as Имя, ad.build as Номер_дома, ad.appartment as Номер_квартиры 
	from people as p join address as ad on p.address_id = ad.id and ad.street = 'first';