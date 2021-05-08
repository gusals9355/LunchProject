create table member(
	name varchar(10) not null,
	email varchar(50) not null,
	gender enum('M','F') not null,
	id varchar(20) primary key,
	pw varchar(20) not null,
	reg_dt datetime default now()
);

create table board(
	no int auto_increment primary key,
	title varchar(30) not null,
	content varchar(1000) not null,
	id varchar(20) not null,
	pw varchar(20),
	readcount int default 0,
	picture varchar(100),
	reg_dt datetime dafualt now(),
	star int(1) not null,
	category varchar(4) not null,
	mapX double(3,16) not null,
	mapy double(4,16) not null
)