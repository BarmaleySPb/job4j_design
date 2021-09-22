create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values 
	('Laptop-1', 100.00), ('Laptop-2', 150.00), ('Laptop-3', 200.00),
	('PC-1', 300.00), ('PC-2', 400.00), ('PC-3', 500.00), 
	('Printer-1', 600.00), ('Printer-2', 700.00), ('Printer-3', 800.00);
insert into people(name) values ('Ivan'), ('Vasiliy'), ('Petr');
insert into devices_people(device_id, people_id) values 
	(1, 1), (4, 1), (8, 1), (2, 2), (5, 2), (3, 3), (9, 3);


select avg(price) as Average_price from devices;

select 
	p.name as name, avg(d.price) as average_price
from 
	devices_people 
join 
	devices d on devices_people.device_id = d.id 
join
	people p on devices_people.people_id = p.id
group by 
	p.name
	
select 
	p.name as name, avg(d.price) as average_price
from 
	devices_people 
join 
	devices d on devices_people.device_id = d.id 
join
	people p on devices_people.people_id = p.id
group by 
	p.name
having avg
	(d.price) > 5000;