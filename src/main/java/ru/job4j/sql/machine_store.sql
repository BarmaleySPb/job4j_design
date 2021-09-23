create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
	id serial primary key,
    name varchar(255)
);

create table gearbox(
	id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
    gearbox_id int references gearbox(id)
);

insert into body (name) values 
	('Body-1'), ('Body-2'), ('Body-3'), ('Body-4'), ('Body-5'), ('Body-6');
insert into engine (name) values 
	('Engine-1'), ('Engine-2'), ('Engine-3'), ('Engine-4'), ('Engine-7');
insert into gearbox (name) values 
	('Gearbox-1'), ('Gearbox-2'), ('Gearbox-3'), ('Gearbox-4'), ('Gearbox-5');
	
insert into car (name, body_id, engine_id, gearbox_id) values 
	('Car-1', 2, 3, 1), ('Car-2', 3, 1, 4), ('Car-3', 4, 4, 3),
	('Car-4', 1, 2, 5);

select 
	car.name as car, b.name as body, e.name as engine, g.name as gearbox
from 
	car 
join 
	body b on car.body_id = b.id 
join
	engine e on car.engine_id = e.id
join
	gearbox g on car.gearbox_id = g.id
group by 
	car.name, b.name, e.name, g.name
order by car.name;

select b.name as not_used
from body b
left join car c on c.body_id = b.id where c.body_id is null;

select e.name as not_used
from engine e
left join car c on c.engine_id = e.id where c.engine_id is null;

select g.name as not_used
from gearbox g
left join car c on c.gearbox_id = g.id where c.gearbox_id is null;
