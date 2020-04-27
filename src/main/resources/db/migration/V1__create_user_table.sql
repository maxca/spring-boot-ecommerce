-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
    id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(250) COLLATE pg_catalog."default" NOT NULL,
    created_datetime timestamp without time zone,
    updated_datetime timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

