create table cars(
	id serial primary key,
	model varchar(255),
	doors smallint,
	taxi boolean
);
insert into cars(model, doors, taxi) values('BMV', '4', 'true');
update cars set taxi = 'false';
select * from cars;
delete from cars;