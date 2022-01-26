DROP TABLE IF EXISTS exchange_rate;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

create table exchange_rate(
	id varchar_ignorecase(50) not null primary key,
	target_currency varchar_ignorecase(200) not null,
	original_currency varchar_ignorecase(200) not null,
	exchange_rate Double not null
);

create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(200) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);

