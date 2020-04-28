CREATE TABLE products
(
    id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    price float,
    stock integer,
    created_datetime timestamp without time zone,
    updated_datetime timestamp without time zone

);