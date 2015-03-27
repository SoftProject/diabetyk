INSERT INTO roles VALUES ('ROLE_USER');
INSERT INTO roles VALUES ('ROLE_MODERATOR');
INSERT INTO roles VALUES ('ROLE_ADMIN');
INSERT INTO roles VALUES ('ROLE_SYS_ADMIN');

INSERT INTO users (username, password, enabled) VALUES ('admin', 'admin', TRUE);

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_SYS_ADMIN');

INSERT INTO userdata (bad_login_count, email, login) VALUES (0, 'admin@example.com', 'admin');
