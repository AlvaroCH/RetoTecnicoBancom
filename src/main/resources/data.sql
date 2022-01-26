insert into exchange_rate (id, original_currency, target_currency, exchange_rate) values ('1', 'USD', 'PEN', 3.0);
insert into exchange_rate (id, original_currency, target_currency, exchange_rate) values ('2', 'EUR', 'PEN', 4.0);

insert into users (username, password, enabled) values ('kdelacruz', '{noop}admin', true);
insert into authorities (username, authority) values ('kdelacruz', 'ROLE_USER');

insert into users (username, password, enabled) values ('wruiz', '{noop}admin', true);
insert into authorities (username, authority) values ('wruiz', 'ROLE_ADMIN');