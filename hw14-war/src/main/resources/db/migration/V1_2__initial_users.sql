insert into users (id, name, login, password, role) values (nextval('users_sequence'), 'Admin', 'admin', '87654321', 'admin');
insert into users (id, name, login, password) values (nextval('users_sequence'), 'USER1', 'user', '12345678');
insert into users (id, name, login, password) values (nextval('users_sequence'), 'USER2', 'user2', '1234');