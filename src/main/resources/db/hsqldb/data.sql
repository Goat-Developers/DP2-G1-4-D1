-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities VALUES ('admin1','admin');
-- Ten owner users, named ownerX with password 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner1','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner2','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner2','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner3','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner3','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner4','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner4','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner5','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner5','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner6','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner6','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner7','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner7','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner8','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner8','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner9','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner9','owner');
INSERT INTO users(username,password,enabled) VALUES ('owner10','0wn3r',TRUE);
INSERT INTO authorities VALUES ('owner10','owner');
-- Six vet users, named vetX with password v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities VALUES ('vet1','veterinarian');
INSERT INTO users(username,password,enabled) VALUES ('vet2','v3t',TRUE);
INSERT INTO authorities VALUES ('vet2','worker');
INSERT INTO users(username,password,enabled) VALUES ('vet3','v3t',TRUE);
INSERT INTO authorities VALUES ('vet3','worker');
INSERT INTO users(username,password,enabled) VALUES ('vet4','v3t',TRUE);
INSERT INTO authorities VALUES ('vet4','worker');
INSERT INTO users(username,password,enabled) VALUES ('vet5','v3t',TRUE);
INSERT INTO authorities VALUES ('vet5','worker');
INSERT INTO users(username,password,enabled) VALUES ('vet6','v3t',TRUE);
INSERT INTO authorities VALUES ('vet6','worker');


INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');


INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (1,1,'Vacuna de la rabia','Para ratas',32.3,'Chema','2022-06-08',30, 'produce ardores');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (2,1,'Vacuna del coronavirus','Para ratas',332,'Chema','2019-06-06',10, 'hecha por chinos');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (3,1,'Vacuna de la peste','Para ratas',32.3,'Chema','2022-06-08',3,'Te quedas como el Chema');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (4,4,'Vacuna de cannabis','Para polen',332,'Chema','2001-06-06',20,'Posibilidad de fiebre en los próximos 3 días');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (5,5,'Vacuna de la uni','Para ratas',31.3,'Chema','2022-06-08',13,'inútil');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (6,6,'Vacuna del virus chino','Para ratas',3324,'Chema','2021-06-06',20,'te puede entrar la rabia');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (7,2,'vacuna contra bebes indefensos','Para ratas',34.3,'Chema','2022-06-08',3,'Te quedas como el Chema');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (8,3,'vacuna para aprobar','Para polen',3322222,'Chema','2001-06-06',5,'Posibilidad de fiebre en los próximos 3 días');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (9,2,'vacuna perros 2','Para prevenir',34.3,'Chema','2022-06-08',3,'Te quedas como el Chema');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (10,3,'vacuna para aprobar','Para polen',3322222,'Chema','2021-06-06',40,'Posibilidad de fiebre en los próximos 3 días');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (11,4,'Vacuna de cannabis','Para polen',332,'Chema','2021-06-06',20,'Posibilidad de fiebre en los próximos 3 días');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (12,5,'Vacuna de cannabis','Para polen',332,'Chema','2021-06-06',20,'Posibilidad de fiebre en los próximos 3 días');
INSERT INTO vaccines(id,pet_type_id,name,information,price,provider,expiration,stock,side_effects) VALUES (13,6,'Vacuna de cannabis','Para polen',332,'Chema','2021-06-06',20,'Posibilidad de fiebre en los próximos 3 días');


INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (1,4,'Dientes serpiente',65.7,'Limpieza de dientes');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (2,1,'Pelo gatos',10,'Corte de pelo');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (3,1,'Dientes gatos',65.7,'Limpieza de dientes');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (4,1,'Garras gatos',0,'Corte de garras');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (5,6,'Garra hamsters',65.7,'Corte de garras');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (6,2,'Garra perros',0,'Corte de garras');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (7,3,'Veneno serpiente',22,'Extracción de veneno para morir');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (8,5,'Alas de pájaro',34,'Corte de alas');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (9,2,'Orejas perros',0,'Corte de orejas');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (10,3,'Piel lagarto',22,'Extracción de piel');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (11,4,'Escamas de serpiente',34,'Limado de escamas');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (12,5,'Pico de pájaro',22,'Quitar el pico al pájaro para que no cante');
INSERT INTO treatments(id,pet_type_id,type,price,description) VALUES (13,6,'Dientes de hamster',34,'Quitar los dientes para que muera de hambre sin poder comer');


INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (1,'Seguro Base Felino',1,'Tiene que ser gato');
INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (2,'Seguro Base Canino ',2,'Tiene que ser perro.');
INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (3,'Seguro Base Lagarto',3,'Tiene que ser lizard');
INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (4,'Seguro Base Reptil',4,'Tiene que ser snake.');
INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (5,'Seguro Base Aves ',5,'Tiene que ser bird');
INSERT INTO insurances_bases(id,name,type_id,conditions) VALUES (6,'Seguro Base Roedores',6,'Tiene que ser hamster.');


INSERT INTO insurance_base_vaccines VALUES (1, 1);
INSERT INTO insurance_base_vaccines VALUES (2, 7);
INSERT INTO insurance_base_vaccines VALUES (3, 2);
INSERT INTO insurance_base_vaccines VALUES (4, 4);
INSERT INTO insurance_base_vaccines VALUES (5, 5);
INSERT INTO insurance_base_vaccines VALUES (6, 6);


INSERT INTO insurance_base_treatments VALUES (1, 2);
INSERT INTO insurance_base_treatments VALUES (2, 6);
INSERT INTO insurance_base_treatments VALUES (3, 7);
INSERT INTO insurance_base_treatments VALUES (4, 1);
INSERT INTO insurance_base_treatments VALUES (5, 8);
INSERT INTO insurance_base_treatments VALUES (6, 5);


INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner2');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner3');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner4');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner5');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner6');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner7');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner8');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner9');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner10');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'Rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'Rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'Neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'Spayed');


INSERT INTO shifts(id, shift_date) VALUES (1, '08:00:00');
INSERT INTO shifts(id, shift_date) VALUES (2, '09:00:00');
INSERT INTO shifts(id, shift_date) VALUES (3, '10:00:00');
INSERT INTO shifts(id, shift_date) VALUES (4, '11:00:00');
INSERT INTO shifts(id, shift_date) VALUES (5, '12:00:00');
INSERT INTO shifts(id, shift_date) VALUES (6, '13:00:00');
INSERT INTO shifts(id, shift_date) VALUES (7, '14:00:00');
INSERT INTO shifts(id, shift_date) VALUES (8, '15:00:00');


INSERT INTO vet_schedule(id) VALUES (1);
INSERT INTO vet_schedule(id) VALUES (2);
INSERT INTO vet_schedule(id) VALUES (3);
INSERT INTO vet_schedule(id) VALUES (4);
INSERT INTO vet_schedule(id) VALUES (5);
INSERT INTO vet_schedule(id) VALUES (6);


INSERT INTO schedule_shifts(vet_schedule_id, shift_id) VALUES (1, 1);
INSERT INTO schedule_shifts VALUES (1, 3);
INSERT INTO schedule_shifts VALUES (1, 5);
INSERT INTO schedule_shifts VALUES (1, 7);


INSERT INTO schedule_shifts VALUES (2, 2);
INSERT INTO schedule_shifts VALUES (2, 4);
INSERT INTO schedule_shifts VALUES (2, 6);
INSERT INTO schedule_shifts VALUES (2, 8);


INSERT INTO schedule_shifts VALUES (3, 1);
INSERT INTO schedule_shifts VALUES (3, 3);
INSERT INTO schedule_shifts VALUES (3, 5);


INSERT INTO schedule_shifts VALUES (4, 2);
INSERT INTO schedule_shifts VALUES (4, 4);
INSERT INTO schedule_shifts VALUES (4, 6);
INSERT INTO schedule_shifts VALUES (4, 8);


INSERT INTO schedule_shifts VALUES (5, 1);
INSERT INTO schedule_shifts VALUES (5, 3);
INSERT INTO schedule_shifts VALUES (5, 5);
INSERT INTO schedule_shifts VALUES (5, 7);


INSERT INTO schedule_shifts VALUES (6, 2);
INSERT INTO schedule_shifts VALUES (6, 4);
INSERT INTO schedule_shifts VALUES (6, 6);
INSERT INTO schedule_shifts VALUES (6, 7);
INSERT INTO schedule_shifts VALUES (6, 8);


INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (1, 'James', 'Carter','vet1', 4,1);
INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (2, 'Helen', 'Leary','vet2',5,2);
INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (3, 'Linda', 'Douglas','vet3',5,3);
INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (4, 'Rafael', 'Ortega','vet4',7,4);
INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (5, 'Henry', 'Stevens','vet5',8,5);
INSERT INTO vets(id,first_name,last_name,username,max_shifts, vet_schedule_id) VALUES (6, 'Sharon', 'Jenkins','vet6',6,6);


INSERT INTO announcements(id,body,header,tag,announcement_date, vet_id,likes) VALUES (1,'esto body','esto header','tag1','2010-09-07',1,0);
INSERT INTO announcements(id,body,header,tag,announcement_date, vet_id,likes) VALUES (2,'esto body mas largo','esto header','caballos','2010-09-07',1,0);
INSERT INTO announcements(id,body,header,tag,announcement_date, vet_id,likes) VALUES (3,'esto body CON MAYUS','esto header','gatos','2010-09-07',1,0);
INSERT INTO announcements(id,body,header,tag,announcement_date, vet_id,likes) VALUES (4,'esto body CON MAYUS','esto header en tiempo','gatos','2022-03-22',1,0);


INSERT INTO specialties VALUES (1, 'Radiology');
INSERT INTO specialties VALUES (2, 'Surgery');
INSERT INTO specialties VALUES (3, 'Dentistry');


INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

