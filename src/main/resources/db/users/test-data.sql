MERGE INTO users(id, firstname, lastname, email, password, enabled, provider)
VALUES (1, 'Keanu', 'Reeves', 'Keanu@gmail.com', '$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e', true, 'local');
MERGE INTO users(id, firstname, lastname, email, password, enabled, provider)
VALUES (2, 'Yurii', 'Kotsiuba', 'jurok3x@gmail.com', '$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e', true, 'local');
MERGE INTO users(id, firstname, lastname, email, password, enabled, provider)
VALUES (3, 'John', 'Smith', 'jsmith@gmail.com', '$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e', true, 'local');
MERGE INTO users(id, firstname, lastname, email, password, enabled, provider)
VALUES (4, 'John', 'Doe', 'Keanu@gmail.com', '$2a$04$MzVXtd4o0y4DOlyHMMLMDeE4/eezrsT5Xad.2lmGr/NkCpwBgvn3e', true, 'local');