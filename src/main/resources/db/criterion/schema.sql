CREATE TABLE IF NOT EXISTS criterions
(
    CRITERION_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    MATERIAL_ID INTEGER NOT NULL,
    TITLE VARCHAR (30),
    DESCRIPTION VARCHAR (255),
    IS_REMOVED boolean DEFAULT FALSE
);