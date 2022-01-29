

MERGE INTO materials (material_id, title, materialtext, startdate, duedate, task, materialtype, maxscore, testurl, topicid, is_removed) VALUES (1, 'title!', 'text', '2001-09-28 00:00:00.000000', '2001-09-28 00:00:00.000000', 'todo something', 'TASK', 12, null, 0, false);
MERGE INTO materials (material_id, title, materialtext, startdate, duedate, task, materialtype, maxscore, testurl, topicid, is_removed) VALUES (2, 'title!', 'text', '2001-09-28 00:00:00.000000', '2001-09-28 00:00:00.000000', 'todo something', 'TASK', 12, null, 0, false);
MERGE INTO links (linkid, material_id, url, linktext) VALUES (0, 1, 'URL1', 'LINK');
MERGE INTO links (linkid, material_id, url, linktext) VALUES (1, 1, 'URL2', 'LINK');
MERGE INTO public.questions (questionid, material_id, question) VALUES (0, 1, 'Q1');
MERGE INTO public.questions (questionid, material_id, question) VALUES (1, 1, 'Q2');