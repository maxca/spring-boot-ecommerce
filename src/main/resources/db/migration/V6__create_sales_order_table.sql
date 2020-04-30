CREATE TABLE sales_order
(
    id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    status character varying(60) COLLATE pg_catalog."default" NOT NULL,
    order_no character varying(60) COLLATE pg_catalog."default" NOT NULL,
    total_price float,
    created_datetime timestamp without time zone

);