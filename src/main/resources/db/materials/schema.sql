
CREATE TABLE IF NOT EXISTS materials
(
    material_id  serial  not null
        constraint materials_pkey
            primary key,
    title        varchar not null,
    materialtext varchar,
    startdate    timestamp,
    duedate      timestamp,
    task         varchar,
    materialtype varchar not null,
    maxscore     integer,
    testurl      varchar,
    topicid      integer not null,
    is_removed   boolean default false
);
create table links
(
    linkid     serial  not null
        constraint links_pkey
            primary key,
    material_id integer not null
        constraint links_materialid_fkey
            references materials,
    url        varchar not null,
    linktext   varchar
);

create table questions
(
    questionid serial  not null
        constraint questions_pkey
            primary key,
    material_id integer not null
        constraint questions_materialid_fkey
            references materials,
    question   varchar not null
);