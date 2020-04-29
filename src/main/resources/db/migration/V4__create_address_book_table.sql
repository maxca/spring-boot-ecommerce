CREATE TABLE address_book
(
    id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    street character varying(100) COLLATE pg_catalog."default" NOT NULL,
    subdistrict character varying(100) COLLATE pg_catalog."default" NOT NULL,
	district character varying(100) COLLATE pg_catalog."default" NOT NULL,
	province character varying(100) COLLATE pg_catalog."default" NOT NULL,
	postal_code character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_datetime timestamp without time zone,
    updated_datetime timestamp without time zone

);