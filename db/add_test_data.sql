-- INSERT INTO lauseet

INSERT INTO Book (id, title, author, ISBN, tags, dateAdded) VALUES ('123', 'Roope Ankan Elämä ja Teot', 'Don Rosa', ' 9789510724378', 'Aku Ankka', '2017-11-13');

INSERT INTO Courses (course_id, courseName) VALUES ('54234', 'Ohjelmoinnin Perusteet');

-- Liitostauluun

INSERT INTO BookRelatedCourses (book_id, course_id) VALUES ('123', '54234');

