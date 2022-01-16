MERGE INTO announcements(id, course_id, text, enabled)
VALUES (1, 2, 'text1', true);
MERGE INTO announcements(id, course_id, text, enabled)
VALUES (2, 1, 'text2', true);
MERGE INTO announcements(id, course_id, text, enabled)
VALUES (3, 4, 'text3', true);
MERGE INTO announcements(id, course_id, text, enabled)
VALUES (4, 3, 'text4', true);