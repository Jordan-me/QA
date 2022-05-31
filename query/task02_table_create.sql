CREATE SCHEMA `exforafeka_1` ;
use `exforafeka_1` ;

CREATE TABLE students (
	student_id INT (11) UNSIGNED NOT NULL,
	student_name VARCHAR(25),
	PRIMARY KEY (student_id)
	);


CREATE TABLE courses (
	cpk INT  UNSIGNED NOT NULL,
	course_id VARCHAR(25),
    course_prior VARCHAR(25),
	PRIMARY KEY (cpk)
	);


CREATE TABLE grades (
	gpk INT  UNSIGNED NOT NULL,
	student_id int,
   course_id VARCHAR(25),
    grade int,
 grade_year int,
	PRIMARY KEY (gpk)
	);


insert into students values (1,"Avi");
insert into students values (2,"Benny");
insert into students values (3,"Gadi");
commit;


insert into courses values (1,"A","None");
insert into courses values (2,"B","A");
insert into courses values (3,"C","A");

commit;

insert into grades values (1,1,"A",52,2022);
insert into grades values (2,1,"B",60,2022);
insert into grades values (3,2,"A",80,2022);


COMMIT;

Select student_name,course_id,grade,grade_year from students join grades g on students.student_id=g.student_id where students.student_id in (
Select student_id from grades where (grade, course_id) in (
Select max(grade), course_id from grades where  grade_year='2022'group by course_id) 
and course_id=g.course_id)




