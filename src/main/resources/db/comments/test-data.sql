MERGE INTO comments(id, author_id, material_id, text, date, announcement_id, user_assignment_id, enabled)
VALUES (1, 2, 4, 'text1', '2022-01-01 14:05:45.954785', 3, 2, true);
MERGE INTO comments(id, author_id, material_id, text, date, announcement_id, user_assignment_id, enabled)
VALUES (2, 3, 1, 'text2', '2022-01-01 14:05:45.954785', 4, 1, true);
MERGE INTO comments(id, author_id, material_id, text, date, announcement_id, user_assignment_id, enabled)
VALUES (3, 4, 3, 'text3', '2022-01-01 14:05:45.954785', 1, 4, true);
MERGE INTO comments(id, author_id, material_id, text, date, announcement_id, user_assignment_id, enabled)
VALUES (4, 1, 2, 'text4', '2022-01-01 14:05:45.954785', 2, 2, true);

