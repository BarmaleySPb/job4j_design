create table company
(
    id integer not NULL,
    name character varying,
    constraint company_pkey primary key (id)
);

create table person
(
    id integer not NULL,
    name character varying,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);

insert into company (id, name) values (1, 'Coca-Cola'), (2, 'Pepsi'), (3, 'Fanta'), (4, 'Sprite'), (5, 'KvasCo');

insert into person (id, name, company_id)
    values (1, 'Zakharov', 1), (2, 'Klimster', 1), (3, 'Timurson', 1), (4, 'Allychan', 3),
		    (5, 'Pukichan', 4), (6, 'Jopychan', 4), (7, 'Bahrudinov', 4), (8, 'Pidorashko', 1),
			 (9, 'Ivan', 2), (10, 'Vasiliy', 5);

select p.name, c.name
	from person p, company c
	where company_id in (
		select c.id
			from company
			where c.id != 5
);

select c.name, count(*)
	from person
	join company c on company_id = c.id
	group by c.name
order by count desc limit 1;