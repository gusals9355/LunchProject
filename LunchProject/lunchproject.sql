create table member(
	name varchar(10) not null,
	email varchar(50) not null,
	gender enum('M','F') not null,
	id varchar(20) primary key,
	pw varchar(20) not null,
	reg_dt datetime default now()
);