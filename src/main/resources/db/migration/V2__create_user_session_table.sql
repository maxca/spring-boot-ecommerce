-- Table: users

-- DROP TABLE users;

CREATE TABLE users_session
(
    id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    expired_datetime timestamp without time zone,
    created_datetime timestamp without time zone,
    updated_datetime timestamp without time zone

);

