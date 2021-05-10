create table member(
	name varchar(10) not null,
	email varchar(50) not null,
	gender enum('M','F') not null,
	id varchar(20) primary key,
	pw varchar(20) not null,
	point int default 0,
	reg_dt datetime default now()
);

create table point(
	id varchar(20),
	point int default 0,
	foreign key(id) references member(id)
);

create table board(
	no int auto_increment primary key,
	title varchar(30) not null,
	content varchar(1000) not null,
	id varchar(20) not null,
	pw varchar(20),
	readcount int default 0,
	picture varchar(1000),
	reg_dt datetime default now(),
	star int(1) not null,
	category varchar(4) not null,
	mapX decimal(20,16) not null,
	mapy double(20,16) not null
);

create table loginlog(
	no int unsigned auto_increment primary key,
	id varchar(20),
	reg_dt datetime default now(),
	foreign key(id) references member(id)
);