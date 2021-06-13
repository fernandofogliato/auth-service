DELETE FROM oauth_client_details;

INSERT INTO "user" (username, email, password, activated)
SELECT * FROM (SELECT 'admin', 'admin@admin.com', '$2y$12$9AeOzgP712/PEu6SNdy4WO40koAQhNOcxyBNRSXuVoPTOwpcbv86K', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM "user" WHERE username = 'admin'
) LIMIT 1;

INSERT INTO authority (name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM authority WHERE name = 'ROLE_USER'
) LIMIT 1;

INSERT INTO authority (name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM authority WHERE name = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'admin' and authority = 'ROLE_USER'
) LIMIT 1;

INSERT INTO user_authority (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM user_authority WHERE username = 'admin' and authority = 'ROLE_ADMIN'
) LIMIT 1;