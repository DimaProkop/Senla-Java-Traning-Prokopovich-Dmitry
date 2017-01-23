create database test;
use test;

create table product (
maker varchar(10) not null,
model varchar(50) not null primary key,
type varchar(50) not null
); 


create table laptop ( 
code int not null primary key, 
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
price decimal null,
screen tinyint not null,
foreign key(model) references product(model)
);

create table pc (
code int not null primary key,
model varchar(50) not null,
speed smallint not null,
ram smallint not null,
hd real not null,
cd varchar(10) not null,
price decimal null,
foreign key(model) references product(model)
);

create table printer (
code int not null primary key,
model varchar(50) not null,
color char(1) not null,
type varchar(10) not null,
price decimal null,
foreign key(model) references product(model)
);