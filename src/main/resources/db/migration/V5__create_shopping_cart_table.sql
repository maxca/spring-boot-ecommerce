CREATE TABLE shopping_cart
(
    id character varying(60) COLLATE pg_catalog."default" PRIMARY KEY,
    user_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    total_price float,
    total_qty int,
    updated_datetime timestamp without time zone,
    created_datetime timestamp without time zone
);

CREATE TABLE cart_item
(
    id character varying(60) COLLATE pg_catalog."default" PRIMARY KEY,
    cart_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    product_id character varying(60) COLLATE pg_catalog."default" NOT NULL,
    quantity integer,
    price float
);