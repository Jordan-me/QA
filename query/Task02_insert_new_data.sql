use `exforafeka_1` ;
insert into students values (4,"123");
insert into students values (5,null);

insert into courses values (4,"V","V");
insert into courses values (5,"None","V");
insert into courses values (6,null,"V");
insert into courses values (7,"F","None");
insert into courses values (8,"J","B");

insert into grades values (5,4,"V",90,2022);
insert into grades values (6,4,null,null,2022);
insert into grades values (7,4,null,90,2022);
insert into grades values (8,4,null,90,2022);
insert into grades values (9,5,"C",90,2022);
insert into grades values (10,2,"D",90,2022);
insert into grades values (12,2,"E",-50,2022);
insert into grades values (11,2,"E",null,2022);
insert into grades values (12,2,"E",-50,2022);
insert into grades values (13,2,null,-50,2022);
insert into grades values (14,2,"A",-100,2021);
insert into grades values (15,2,"B",-100,00);
insert into grades values (16,2,"B",-100,2000);
insert into grades values (17,2,"B",-100,2020);
insert into grades values (18,2,"B",-100,-2022);
insert into grades values (19,2,"C",-100,-2022);
insert into grades values (20,2,"A",100,-2022);
insert into grades values (21,2,"F",100,-2021);
insert into grades values (22,2,"J",100,-2021);
insert into grades values (23,2,"F",100,2021);
insert into grades values (24,2,"J",100,-2022);

Select student_name,course_id,grade,grade_year from students join grades g on students.student_id=g.student_id where students.student_id in (
Select student_id from grades where (grade, course_id) in (
Select max(grade), course_id from grades where  grade_year='2022'group by course_id) 
and course_id=g.course_id)
