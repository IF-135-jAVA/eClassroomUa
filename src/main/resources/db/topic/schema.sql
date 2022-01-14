CREATE TABLE IF NOT EXISTS topics
(
    TOPIC_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CLASSROOM_ID INTEGER NOT NULL,
    TITLE VARCHAR (30),
    IS_REMOVED boolean DEFAULT FALSE
);