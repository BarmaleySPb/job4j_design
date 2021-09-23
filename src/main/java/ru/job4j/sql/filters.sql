create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float
);

insert into type(name) values ('Cheese'), ('Meat'), ('Groat'), ('Fruit'), ('Ice cream');
insert into product(name, type_id, expired_date, price) values 
	('Russian cheese', 1, date '24-08-2021', 500.00), ('Dutch cheese', 1, date '12-12-2021', 1000.00),
	('Pork', 2, date '20-09-2021', 300.00), ('Beef', 2, date '29-09-2021', 600.00), 
	('Rabbit', 2, date '27-09-2021', 800.00), ('Chicken', 2, date '05-10-2021', 200),
	('Buckwheat', 3, date '22-05-2022', 100.00), ('Сorn grits', 3, date '12-07-2022', 90.00),
	('Bananas', 4, date '27-10-2021', 100.00), ('Oranges', 4, date '05-10-2021', 150),
	('Ice cream "New"', 5, date '27-12-2021', 60.00), ('Ice cream "Russian"', 5, date '05-12-2021', 50),
	('Ice cream "Delicious"', 5, date '12-12-2021', 100);

select * from product;

select * from product where type_id = (select type.id from type where name = 'Cheese');

select * from product where name like '%Ice cream%';

select * from product where expired_date < current_date;

select name, price as max_price from product where price = (select max(price) from product);

select t.name, count(*)
from product
join type t on type_id = t.id
group by t.name;

select *
from product
where
	type_id = (select type.id from type where name = 'Cheese')
	or
	type_id = (select type.id from type where name = 'Groat');


select t.name, count(*)
from product
join type t on type_id = t.id
group by t.name
having count(t.name) < 3;

select product.name as product, t.name as type
from product
join type t on product.type_id = t.id
group by product.name, t.name;