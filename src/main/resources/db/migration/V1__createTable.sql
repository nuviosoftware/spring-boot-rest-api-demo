CREATE TABLE product (
	id BIGSERIAL PRIMARY KEY,
	name varchar(255) NULL,
	price numeric(19,2) NULL
);

create sequence product_sequence start with 1 increment by 1;