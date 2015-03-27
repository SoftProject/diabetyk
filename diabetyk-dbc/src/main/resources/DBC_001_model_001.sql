CREATE TABLE schema_version
(
  uid     INTEGER                     NOT NULL,
  module  CHARACTER VARYING(255)      NOT NULL,
  mid     INTEGER                     NOT NULL,
  dateins TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  CONSTRAINT schema_version_pkey PRIMARY KEY (uid)
);

CREATE UNIQUE INDEX schema_version_uid_mid_idx
ON schema_version
USING BTREE (uid, mid);

INSERT INTO schema_version (uid, module, mid) VALUES (
  1,
  'model',
  1
);

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;

CREATE SCHEMA audit;

ALTER SCHEMA audit
OWNER TO postgres;
GRANT ALL ON SCHEMA audit TO "diabetyk-user";

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
