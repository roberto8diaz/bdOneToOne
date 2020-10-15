insert into course(id,name, last_updated_date,create_date,is_deleted) values(1001,'JPA IN JOKE',sysdate(),sysdate(),false);
insert into course(id,name, last_updated_date, create_date,is_deleted) values(1002,'react',sysdate(),sysdate(),false);
insert into course(id,name, last_updated_date, create_date,is_deleted) values(1003,'ionic',sysdate(),sysdate(),false);


insert into passport(id,number) values(4001,'E12345');
insert into passport(id,number) values(4002,'E54321');
insert into passport(id,number) values(4003,'E98765');

insert into student(id,name,passport_id) values(2001,'juan',4001);
insert into student(id,name,passport_id) values(2002,'pedro',4002);
insert into student(id,name,passport_id) values(2003,'luis',4003);

insert into review(id,rating,description,course_id) values(5001,5,'wondeful',1001);
insert into review(id,rating,description,course_id) values(5002,4,'good',1002);
insert into review(id,rating,description,course_id) values(5003,3,'gorgeous',1003);

insert into student_course(student_id,course_id)values(2001,1001);
insert into student_course(student_id,course_id)values(2002,1001);
insert into student_course(student_id,course_id)values(2003,1001);
insert into student_course(student_id,course_id)values(2001,1003);