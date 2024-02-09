DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS posts;

create table users (
	idUser varchar_ignorecase(50) not null primary key,
	cellphone varchar_ignorecase(200) not null,
	name varchar_ignorecase(200) not null,
	lastName varchar_ignorecase(200) not null,
	password varchar_ignorecase(200) not null,
	creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	lastModificationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
create table posts (
	idPost varchar_ignorecase(50) not null primary key,
	text varchar_ignorecase(200) not null,
	creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastModificationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	constraint fk_post_users foreign key(idUser) references users(idUser)

);



