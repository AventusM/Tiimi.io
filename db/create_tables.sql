-- CREATE TABLE lauseet

CREATE TABLE Book(
id SERIAL PRIMARY KEY NOT NULL,
title varchar(50) NOT NULL,
author varchar(50) NOT NULL,
ISBN varchar (50),
tags varchar(50),
dateAdded DATE NOT NULL
);

CREATE TABLE Courses(
course_id SERIAL PRIMARY KEY NOT NULL,
courseName varchar(50)
);

-- Liitostaulu

CREATE TABLE BookPrequisiteCourses(
book_id int REFERENCES Book(id) NOT NULL,
course int REFERENCES Courses(course_id) NOT NULL 
);

-- Liitostaulu

CREATE TABLE BookRelatedCourses(
book_id int REFERENCES Book(id) NOT NULL,
course_id int REFERENCES Courses(course_id) NOT NULL 
);
