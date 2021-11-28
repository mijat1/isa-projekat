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