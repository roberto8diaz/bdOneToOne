insert into course(id,name, last_updated_date,create_date) values(1001,'JPA IN JOKE',sysdate(),sysdate());
insert into course(id,name, last_updated_date, create_date) values(1002,'react',sysdate(),sysdate());
insert into course(id,name, last_updated_date, create_date) values(1003,'ionic',sysdate(),sysdate());

insert into passport(id,number) values(4001,'E12345');
insert into passport(id,number) values(4002,'E54321');
insert into passport(id,number) values(4003,'E98765');

insert into student(id,name,passport_id) values(2001,'juan',4001);
insert into student(id,name,passport_id) values(2002,'pedro',4002);
insert into student(id,name,passport_id) values(2003,'luis',4003);

insert into review(id,rating,description) values(5001,'5','wondeful');
insert into review(id,rating,description) values(5002,'4','good');
insert into review(id,rating,description) values(5003,'3','gorgeous');