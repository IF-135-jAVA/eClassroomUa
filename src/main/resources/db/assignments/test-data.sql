MERGE INTO assignment_statuses (id, title) VALUES (1, 'IN_PROGRESS');
MERGE INTO assignment_statuses (id, title) VALUES (2, 'REVIEWED');
MERGE INTO assignment_statuses (id, title) VALUES (3, 'DONE');

MERGE INTO user_assignments (id, material_id, user_id, assignment_status_id, submission_date, grade, feedback, enabled)
VALUES (1, 1, 2, 1, '2022-01-26 19:25:00', 10, 'Good', true);
MERGE INTO user_assignments (id, material_id, user_id, assignment_status_id, submission_date, grade, feedback, enabled)
VALUES (2, 1, 3, 3, '2022-01-28 14:15:00', 9, 'Almost good', true);
MERGE INTO user_assignments (id, material_id, user_id, assignment_status_id, submission_date, grade, feedback, enabled)
VALUES (3, 3, 3, 3, '2022-01-28 14:15:00', 9, 'Almost good', true);
MERGE INTO user_assignments (id, material_id, user_id, assignment_status_id, submission_date, grade, feedback, enabled)
VALUES (4, 3, 2, 3, '2022-01-19 12:15:00', 10, 'Good', true);