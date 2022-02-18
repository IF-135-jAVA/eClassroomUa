MERGE INTO classrooms(classroom_id, user_id, title, session, description, enabled)
VALUES (1, 5, 'English Language', 'Present Simple', 'The Present Simple Tense', true);
MERGE INTO classrooms(classroom_id, user_id, title, description, enabled)
VALUES (2, 1, 'Ukraine Language', 'Plurals, You will learn how to make plurals in Ukrainian', true);
MERGE INTO classrooms(classroom_id, user_id, title, description, enabled)
VALUES (3, 4, 'Mathematics', 'General and overarching topics collections: Introductory exposition pertaining to mathematics in general', true);
MERGE INTO classrooms(classroom_id, user_id, title, description, enabled)
VALUES (4, 3, 'Biology', 'Genetics, Genomes, Chromosomes and the Cell Cycle', true);