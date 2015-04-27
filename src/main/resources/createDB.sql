create table admins (admin_id int auto_increment, login varchar(10), passw varchar(10), primary key (admin_id));
insert into admins (login, passw) values ('root', 'root'), ('admin', 'admin');

create table clients (client_id int auto_increment, login varchar(10), passw varchar(10), 
	name varchar(20), email varchar(20), phone varchar(15), is_bad boolean default false,
  primary key (client_id));
insert into clients (login, passw, name, email, phone, is_bad) values
	('alex', 'alex12', 'Alex Ivanov', 'alex@mail.ru', '345890784', 0),
    ('lola', '1234', 'Lolita Johanson', 'lola@gmail.com', '567209590', 0),
    ('andrey', 'and', 'Андрей Коваленко', 'andrey@mail.com', '12345093', 0),
    ('sara', 'aras', 'Sara Goodman', 'sara@mail.ru', '450924575', 1),
    ('jack', 'jack', 'Jack White', 'jack@gmail.com', '973859258', 1),
    ('helena', 'lena', 'Helena Brandebovich', 'helena@gmail.com', '884521111', 0),
    ('den', 'den1', 'Den Brown', 'den@mail.com', '459385760', 0);
    

create table countries (country_id int auto_increment, country_name_en varchar(20) NOT NULL, 
	country_name_ru varchar(20) NOT NULL, primary key (country_id));
insert into countries (nameEn, nameRu) values ('Sweden', 'Швеция'), ('Great Britain', 'Великобритания'),
	('Sri Lanka', 'Шри-Ланка'), ('Greece', 'Греция');

create table products (product_id int auto_increment, product_name_en varchar(30) NOT NULL, 
	product_name_ru varchar(30) NOT NULL, country_id int NOT NULL, price int NOT NULL,
    primary key (product_id), foreign key (country_id) references countries (country_id));
insert into flights (nameEn, nameRu, country_id, price) values
	('Kiev-Stockholm', 'Киев-Стокгольм', 1, 4380), ('Kiev-Malmo', 'Киев-Мальме', 1, 5050),
    ('Kiev-London', 'Киев-Лондон', 2, 3950), ('Kiev-Liverpool', 'Киев-Ливерпуль', 2, 4500),
    ('Kiev-Glasgow', 'Киев-Глазго', 2, 5700), ('Kiev-Colombo', 'Киев-Коломбо', 3, 9830),
    ('Kiev-Athens', 'Киев-Афины', 4, 6100), ('Kiev-Heraklion', 'Киев-Ираклион', 4, 5820),
    ('Kiev-Thessaloniki', 'Киев-Салоники', 4, 5550);

insert into countries (nameEn, nameRu) values ('Germany', 'Германия'), ('France', 'Франция'),
	('United States', 'США'), ('Italy', 'Италия'), ('Netherlands', 'Нидерланды'), ('Norway', 'Норвегия');
insert into flights (nameEn, nameRu, country_id, price) values
	('Kiev-Munich', 'Киев-Мюнхен', 5, 4080), ('Kiev-Dusseldorf', 'Киев-Дюссельдорф', 5, 5650),
    ('Kiev-Berlin', 'Киев-Берлин', 5, 5850), ('Kiev-Hamburg', 'Киев-Гамбург', 5, 5600),
    ('Kiev-Paris', 'Киев-Париж', 6, 7700), ('Kiev-Leon', 'Киев-Леон', 6, 8830),
    ('Kiev-New York', 'Киев-Нью-Йорк', 7, 16100), ('Kiev-Washington', 'Киев-Вашингтон', 7, 15000),
    ('Kiev-Roma', 'Киев-Рим', 8, 6550), ('Kiev-Milan', 'Киев-Милан', 8, 5900),
    ('Kiev-Napoli', 'Киев-Неаполь', 8, 5500), ('Kiev-Amsterdam', 'Киев-Амстердам', 9, 7500),
    ('Kiev-The Hague', 'Киев-Гаага', 9, 7770), ('Kiev-Rotterdam', 'Киев-Роттердам', 9, 8130),
    ('Kiev-Oslo', 'Киев-Осло', 10, 6900), ('Kiev-Trondheim', 'Киев-Трондхейм', 10, 6120),
    ('Kiev-Bergen', 'Киев-Берген', 10, 7050);

create table orders (order_id int auto_increment, order_date date, client_id int, amount int,
   primary key (order_id), isApproved boolean default false, foreign key (client_id) references clients (client_id));

create table order_contents (order_id int, product_id int, foreign key (order_id) references orders(order_id),
  foreign key (product_id) references products(product_id));