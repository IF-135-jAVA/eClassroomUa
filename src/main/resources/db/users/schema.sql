CREATE TABLE USERS
(
    ID int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FIRSTNAME varchar(255),
    LASTNAME varchar(255),
    EMAIL varchar(255),
    PASSWORD varchar(255),
    ENABLED boolean
);