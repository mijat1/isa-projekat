--AUTHORITY
insert into authority (id, name) values ('7852aa5e-7040-4d99-8255-537a0b226c75','ROLE_CLIENT');
insert into authority (id, name) values ('563e9925-cff6-42b7-99fa-6b1235f67655','ROLE_SYSADMIN');
insert into authority (id, name) values ('09af8857-2f78-4eec-970f-059d3ed4589f','ROLE_BOATOWNER');
insert into authority (id, name) values ('ef9a3723-a72e-44ec-83ac-9d748fd0240f','ROLE_COTTAGEOWNER');
insert into authority (id, name) values ('a1e3bac1-6093-4705-b835-eed75c3e5f21','ROLE_FISHINGINSTRUCTOR');


--sysadmins-users
insert into users (id, email, password, name, surname, phone_Number, active, address,user_type) values ('44444d47-1a8a-4ae1-b109-af7b56e94788','sysadmin@gmail.com','$2a$10$TyNl6ipLWyDE/TfFM3uRse0SVPP4Rz7.mdZdDK3zqjKZqtKtJ3pf2','Zec','Njekezovic','0612345',true,'Kosovska 22','SYSADMIN');

--cilient-users
insert into users (id, email, password, name, surname, phone_Number, active, address,user_type) values ('22793162-52d3-11eb-ae93-0242ac130002','client@gmail.com', '$2a$10$TyNl6ipLWyDE/TfFM3uRse0SVPP4Rz7.mdZdDK3zqjKZqtKtJ3pf2','Njeke','Zeke','0623333',true,'Kisacka 22','CLIENT');

--boatowner-users
insert into users (id, email, password, name, surname, phone_Number, active, address,user_type) values ('80c86094-ba60-11eb-8529-0242ac130003','bowner@gmail.com', '$2a$10$TyNl6ipLWyDE/TfFM3uRse0SVPP4Rz7.mdZdDK3zqjKZqtKtJ3pf2','Nikola','Jovic','064555787',true,'Skolska 12','BOATOWNER');                                

--cottageowner-users
insert into users (id, email, password, name, surname, phone_Number, active, address,user_type) values ('07a2c302-b584-11eb-8529-0242ac130003','cowner@gmail.com', '$2a$10$TyNl6ipLWyDE/TfFM3uRse0SVPP4Rz7.mdZdDK3zqjKZqtKtJ3pf2','Marija','Jovanovic','064555787',true,'Partizanska 11','COTTAGEOWNER');                                

--fishinginstructor-users
insert into users (id, email, password, name, surname, phone_Number, active, address,user_type) values ('aef9fa80-b584-11eb-8529-0242ac130003','fishing@gmail.com', '$2a$10$TyNl6ipLWyDE/TfFM3uRse0SVPP4Rz7.mdZdDK3zqjKZqtKtJ3pf2','Milica','Peric','06388929',true,'Nikolajevska 12','FISHINGINSTRUCTOR'); 
--SYSTEM ADMIN
insert into system_admin(id) values ('44444d47-1a8a-4ae1-b109-af7b56e94788');

--CLIENT
insert into client(id) values ('22793162-52d3-11eb-ae93-0242ac130002');

--BOATOWNER
insert into boat_owner(id) values ('80c86094-ba60-11eb-8529-0242ac130003');

--COTTAGEOWNER
insert into cottage_owner(id) values ('07a2c302-b584-11eb-8529-0242ac130003');

--FISHINGINSTRUCTOR
insert into fishing_instructor(id) values ('aef9fa80-b584-11eb-8529-0242ac130003');


--USER-AUTHORITY
insert into user_authority (user_id, authority_id) values ('44444d47-1a8a-4ae1-b109-af7b56e94788', '563e9925-cff6-42b7-99fa-6b1235f67655');
insert into user_authority (user_id, authority_id) values ('22793162-52d3-11eb-ae93-0242ac130002', '7852aa5e-7040-4d99-8255-537a0b226c75');
insert into user_authority (user_id, authority_id) values ('80c86094-ba60-11eb-8529-0242ac130003', '09af8857-2f78-4eec-970f-059d3ed4589f');
insert into user_authority (user_id, authority_id) values ('07a2c302-b584-11eb-8529-0242ac130003', 'ef9a3723-a72e-44ec-83ac-9d748fd0240f');
insert into user_authority (user_id, authority_id) values ('aef9fa80-b584-11eb-8529-0242ac130003', 'a1e3bac1-6093-4705-b835-eed75c3e5f21');

--navi equipment
insert into navigation_equipment(id,name) values ('5502767a-4c64-11ec-81d3-0242ac130003','GPS');
insert into navigation_equipment(id,name) values ('97c86212-4c64-11ec-81d3-0242ac130003','RADAR');
insert into navigation_equipment(id,name) values ('9ee4e9da-4c64-11ec-81d3-0242ac130003','VHF');
insert into navigation_equipment(id,name) values ('a6bd8e28-4c64-11ec-81d3-0242ac130003','FISHFINDER');



--album
insert into album(id) values ('581dc9d4-4c66-11ec-81d3-0242ac130003');
insert into album(id) values ('0756466a-4c6c-11ec-81d3-0242ac130003');
insert into album(id) values ('0d882300-4c6c-11ec-81d3-0242ac130003');

--images 
insert into images(id,file_name) values ('71e28e12-4c67-11ec-81d3-0242ac130003','350z.jpg');
insert into images(id,file_name) values ('1ea0cfca-4c6c-11ec-81d3-0242ac130003','vikendica.jpg');
insert into images(id,file_name) values ('2675dc4a-4c6c-11ec-81d3-0242ac130003','350z2.jpg');


--album_images
insert into album_images(album_id, images_id) values ('581dc9d4-4c66-11ec-81d3-0242ac130003','71e28e12-4c67-11ec-81d3-0242ac130003');
insert into album_images(album_id, images_id) values ('0756466a-4c6c-11ec-81d3-0242ac130003','1ea0cfca-4c6c-11ec-81d3-0242ac130003');

--units
insert into unit(id, name, address, description, rules, album_id, price, cancellation, percent_of_cancel,unit_type) values ('4ee275b4-4c65-11ec-81d3-0242ac130003', 'Brod Marina','Petra Drapsina 13','Brod je opremljen savremenom opremom i omogcava vam komforno visesatno putovanje', 'Trcanje skakanje i sve ostalo je dozvoljeno','581dc9d4-4c66-11ec-81d3-0242ac130003','1520', 'NEEDTOPAY','20','BOAT');
insert into unit(id, name, address, description, rules, album_id, price, cancellation, percent_of_cancel,unit_type) values ('529b7ab2-78b0-11ec-90d6-0242ac120003', 'Brod Pirate','Milana Rakica 18','Prevoz robe reko reke', 'Trcanje skakanje i sve ostalo je dozvoljeno','581dc9d4-4c66-11ec-81d3-0242ac130003','1620', 'FREE','0','BOAT');
insert into unit(id, name, address, description, rules, album_id, price, cancellation, percent_of_cancel,unit_type) values ('f3001024-4c6b-11ec-81d3-0242ac130003', 'Vila Moskva','Temerinska 25','Vikendica sadrzi sve', 'Zabranjeno paljenje vatre u dvoristu','0756466a-4c6c-11ec-81d3-0242ac130003','1250', 'NEEDTOPAY','25','COTTAGE');
insert into unit(id, name, address, description, rules, album_id, price, cancellation, percent_of_cancel,unit_type) values ('87a2fbb2-7b9a-11ec-90d6-0242ac120003', 'Petrovic Apartmani','Kisacka 25','Ova vikendica je nove gradnje', 'Zabranjeno paljenje vatre u dvoristu','0756466a-4c6c-11ec-81d3-0242ac130003','1550', 'NEEDTOPAY','25','COTTAGE');
insert into unit(id, name, address, description, rules, album_id, price, cancellation, percent_of_cancel,unit_type) values ('fb80c9d2-4c6b-11ec-81d3-0242ac130003', 'Casovi pecanja','Brace Ribnikar 18','Najbolji instruktor u gradu.', 'Dozvoljeno pecanje iskljucivo u prisustvu instruktora','0d882300-4c6c-11ec-81d3-0242ac130003','2000', 'NEEDTOPAY','30','COURSE');

--boats
insert into boat(id,power,length,max_speed,capacity,owner_id) values ('4ee275b4-4c65-11ec-81d3-0242ac130003', '120', '520','20','6','80c86094-ba60-11eb-8529-0242ac130003');
insert into boat(id,power,length,max_speed,capacity,owner_id) values ('529b7ab2-78b0-11ec-90d6-0242ac120003', '120', '520','20','6','80c86094-ba60-11eb-8529-0242ac130003');

--cottages
insert into cottage(id,number_of_rooms,number_beds_per_room,owner_id) values ('f3001024-4c6b-11ec-81d3-0242ac130003','4','2','07a2c302-b584-11eb-8529-0242ac130003');
insert into cottage(id,number_of_rooms,number_beds_per_room,owner_id) values ('87a2fbb2-7b9a-11ec-90d6-0242ac120003','3','2','07a2c302-b584-11eb-8529-0242ac130003');

--courses
insert into fishing_course(id,biography,instructor_id) values('fb80c9d2-4c6b-11ec-81d3-0242ac130003', 'Visestruki evropski sampion u ribolovu','aef9fa80-b584-11eb-8529-0242ac130003');

--fishing equipment
insert into fishing_equipment(id,name) values ('3b0de840-4c6d-11ec-81d3-0242ac130003','FISHINGRODS');

--fish equipment
insert into fish_equipment(unit_id,fe_id) values ('fb80c9d2-4c6b-11ec-81d3-0242ac130003','3b0de840-4c6d-11ec-81d3-0242ac130003');

--navi
insert into navi_equipment(boat_id,ne_id) values('4ee275b4-4c65-11ec-81d3-0242ac130003','5502767a-4c64-11ec-81d3-0242ac130003');

--dodatne usluge
insert into other_tag(id,name,price) values('6915ec90-70b9-11ec-90d6-0242ac120003','Klima','120');
insert into other_tag(id,name,price) values('84da216c-70b9-11ec-90d6-0242ac120003','WIFI','0');
insert into other_tag(id,name,price) values('699dcfd8-70ba-11ec-90d6-0242ac120003','Parking','350');
insert into other_tag(id,name,price) values('9d4bf1b2-70b9-11ec-90d6-0242ac120003','Minibar','400');
insert into other_tag(id,name,price) values('7715339a-70ba-11ec-90d6-0242ac120003','Pet friendly','0');

--tags
insert into tags(unit_id,tag_id) values('4ee275b4-4c65-11ec-81d3-0242ac130003','9d4bf1b2-70b9-11ec-90d6-0242ac120003');
insert into tags(unit_id,tag_id) values('4ee275b4-4c65-11ec-81d3-0242ac130003','7715339a-70ba-11ec-90d6-0242ac120003');
insert into tags(unit_id,tag_id) values('f3001024-4c6b-11ec-81d3-0242ac130003','699dcfd8-70ba-11ec-90d6-0242ac120003');
insert into tags(unit_id,tag_id) values('f3001024-4c6b-11ec-81d3-0242ac130003','84da216c-70b9-11ec-90d6-0242ac120003');
insert into tags(unit_id,tag_id) values('f3001024-4c6b-11ec-81d3-0242ac130003','6915ec90-70b9-11ec-90d6-0242ac120003');

--available period
insert into available_period(id, start_date,end_date, start_time,end_time, owner_id, unit_id) values('2fb41318-60d4-11eb-ae93-0242ac130002', '2022-01-02 14:44:03','2022-04-25 23:44:03', 8, 17, '80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into available_period(id, start_date,end_date, start_time,end_time, owner_id, unit_id) values('ed317f36-78b0-11ec-90d6-0242ac120003', '2022-01-02 14:44:03','2022-04-25 23:44:03', 8, 17, '80c86094-ba60-11eb-8529-0242ac130003','529b7ab2-78b0-11ec-90d6-0242ac120003');
insert into available_period(id, start_date,end_date, start_time,end_time, owner_id, unit_id) values('0c63b9e2-7b99-11ec-90d6-0242ac120003', '2022-01-02 14:44:03','2022-04-25 23:44:03', 8, 17, '07a2c302-b584-11eb-8529-0242ac130003','f3001024-4c6b-11ec-81d3-0242ac130003');
insert into available_period(id, start_date,end_date, start_time,end_time, owner_id, unit_id) values('c55bd7f8-7b9a-11ec-90d6-0242ac120003', '2022-01-02 14:44:03','2022-04-25 23:44:03', 8, 17, '07a2c302-b584-11eb-8529-0242ac130003','87a2fbb2-7b9a-11ec-90d6-0242ac120003');
--grade
insert into unit_feedback(id,date,grade,client_id,unit_id) values('2f8d7e22-78af-11ec-90d6-0242ac120003','2022-01-02 14:44:03','4','22793162-52d3-11eb-ae93-0242ac130002','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into unit_feedback(id,date,grade,client_id,unit_id) values('cad9aca2-78af-11ec-90d6-0242ac120003','2022-01-03 14:44:03','5','22793162-52d3-11eb-ae93-0242ac130002','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into unit_feedback(id,date,grade,client_id,unit_id) values('71bc6d84-78b0-11ec-90d6-0242ac120003','2022-01-03 14:44:03','5','22793162-52d3-11eb-ae93-0242ac130002','529b7ab2-78b0-11ec-90d6-0242ac120003');
insert into unit_feedback(id,date,grade,client_id,unit_id) values('797c5d5e-78b0-11ec-90d6-0242ac120003','2022-01-03 14:44:03','5','22793162-52d3-11eb-ae93-0242ac130002','529b7ab2-78b0-11ec-90d6-0242ac120003');

--reservation
insert into reservation(id,start_date_time,end_date_time,reservation_status,reservation_type,price,client_id,owner_id,unit_id) values('abe05812-7a32-11ec-90d6-0242ac120003','2022-01-02 14:44:03','2022-01-06 14:44:03','FINISHED','BOAT','6480','22793162-52d3-11eb-ae93-0242ac130002','80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into reservation(id,start_date_time,end_date_time,reservation_status,reservation_type,price,client_id,owner_id,unit_id) values('80f61c2a-7a39-11ec-90d6-0242ac120003','2022-01-07 14:44:03','2022-01-12 14:44:03','FINISHED','BOAT','9320','22793162-52d3-11eb-ae93-0242ac130002','80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into reservation(id,start_date_time,end_date_time,reservation_status,reservation_type,price,client_id,owner_id,unit_id) values('89fa8162-7a39-11ec-90d6-0242ac120003','2021-11-02 14:44:03','2021-11-16 14:44:03','FINISHED','BOAT','12580','22793162-52d3-11eb-ae93-0242ac130002','80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into reservation(id,start_date_time,end_date_time,reservation_status,reservation_type,price,client_id,owner_id,unit_id) values('8de035ba-7a39-11ec-90d6-0242ac120003','2021-12-05 14:44:03','2021-12-07 14:44:03','FINISHED','BOAT','3240','22793162-52d3-11eb-ae93-0242ac130002','80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');
insert into reservation(id,start_date_time,end_date_time,reservation_status,reservation_type,price,aprice,owner_id,unit_id) values('2c3561e0-7b7f-11ec-90d6-0242ac120003','2022-01-25 14:44:03','2022-01-27 14:44:03','ACTION','BOAT','3240','1520','80c86094-ba60-11eb-8529-0242ac130003','4ee275b4-4c65-11ec-81d3-0242ac130003');