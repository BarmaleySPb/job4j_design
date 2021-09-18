create table models(
     id serial primary key,
     name varchar(255)
);

create table types(
     id serial primary key,
     name varchar(255)
);
 
create table models_types(
     id serial primary key,
     model_id int references types(id),
     type_id int references models(id)
);
 
insert into models(name) values ('BMW');
insert into models(name) values ('LADA');
insert into models(name) values ('TOYOTA');

insert into types(name) values ('Sedan');
insert into types(name) values ('Coupe');
insert into types(name) values ('Hatch-back');
insert into types(name) values ('SUV');

insert into models_types(models_id, types_id) values (2, 1);
insert into models_types(models_id, types_id) values (2, 4);
insert into models_types(models_id, types_id) values (2, 2);
insert into models_types(models_id, types_id) values (1, 2);
insert into models_types(models_id, types_id) values (1, 3);
insert into models_types(models_id, types_id) values (1, 4);
insert into models_types(models_id, types_id) values (3, 1);
insert into models_types(models_id, types_id) values (3, 3);