CREATE TABLE IF NOT EXISTS CLASSROOMS
(
    USER_ID integer(25),
    TITLE varchar(255),
    SESSION varchar(255),
    DESCRIPTION varchar(255),
    ENABLED boolean,
    CLASSROOM_ID uuid PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS STUDENTS_CLASSROOMS
(
    USER_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CLASSROOM_ID uuid
);

CREATE TABLE IF NOT EXISTS TEACHERS_CLASSROOMS
(
    USER_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CLASSROOM_ID uuid
);