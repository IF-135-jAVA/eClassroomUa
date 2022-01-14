CREATE TABLE IF NOT EXISTS COMMENTS
(

    ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    AUTHOR_ID int,
    MATERIAL_ID int,
    TEXT varchar,
    DATE timestamp,
    ANNOUNCEMENT_ID int,
    USER_ASSIGNMENT_ID int,
    ENABLED boolean
);
