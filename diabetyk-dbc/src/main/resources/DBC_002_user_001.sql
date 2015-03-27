INSERT INTO schema_version (uid, module, mid) VALUES (
  2,
  'user',
  1
);

CREATE TABLE roles
(
  role_name CHARACTER VARYING(255) NOT NULL,
  CONSTRAINT roles_pkey PRIMARY KEY (role_name)
)
WITH (
OIDS = FALSE
);

GRANT SELECT, UPDATE, INSERT ON TABLE roles TO "diabetyk-user";

ALTER TABLE roles OWNER TO "diabetyk-user";

INSERT INTO roles VALUES ('ROLE_USER');
INSERT INTO roles VALUES ('ROLE_MODERATOR');
INSERT INTO roles VALUES ('ROLE_ADMIN');
INSERT INTO roles VALUES ('ROLE_SYS_ADMIN');
