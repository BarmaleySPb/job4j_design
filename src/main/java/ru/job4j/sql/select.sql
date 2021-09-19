create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
	values('fish carp', 25000, date '1500-10-02');
insert into fauna (name, avg_age, discovery_date)
	values('fish shark', 20000, date '1600-11-12');
insert into fauna (name, avg_age, discovery_date)
	values('dog', 15000, date '1400-01-01');
insert into fauna (name, avg_age, discovery_date)
	values('cat', 35000, date '1300-02-02');
insert into fauna (name, avg_age, discovery_date)
	values('cow', 45000, date '1200-04-04');
insert into fauna (name, avg_age, discovery_date)
	values('pig', 45000, null);
	
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 14999 and avg_age < 20001;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < date '1950-01-01';