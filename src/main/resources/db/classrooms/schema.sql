CREATE TABLE IF NOT EXISTS CLASSROOMS
(
    CLASSROOM_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    USER_ID integer(25),
    TITLE varchar(255),
    SESSION varchar(255),
    DESCRIPTION varchar(255),
    CODE varchar(255)
);

CREATE TABLE IF NOT EXISTS STUDENTS_CLASSROOMS
(
    USER_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CLASSROOM_ID integer(25)
);

CREATE TABLE IF NOT EXISTS TEACHERS_CLASSROOMS
(
    USER_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CLASSROOM_ID integer(25)
);

