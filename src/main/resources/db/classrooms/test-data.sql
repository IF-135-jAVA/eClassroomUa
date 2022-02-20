MERGE INTO classrooms(user_id, title, session, description, enabled, classroom_id)
VALUES (5, 'English Language', 'Present Simple', 'The Present Simple Tense', true, '04aa5bec-01aa-4dc9-b57d-37669dd72c47');
MERGE INTO classrooms(user_id, title, description, enabled, classroom_id)
VALUES (1, 'Ukraine Language', 'Plurals, You will learn how to make plurals in Ukrainian', true, '4c76dff2-fe85-4706-912e-70ecb292fe6d');
MERGE INTO classrooms(user_id, title, description, enabled, classroom_id)
VALUES (4, 'Mathematics', 'General and overarching topics collections: Introductory exposition pertaining to mathematics in general', true, 'f3128742-6e1f-45dc-a01f-2d3d1f2c6e2a');
MERGE INTO classrooms(user_id, title, description, enabled, classroom_id)
VALUES (3, 'Biology', 'Genetics, Genomes, Chromosomes and the Cell Cycle', true, '2f7a282f-0aac-4f56-82e8-4f3fe363b29c');