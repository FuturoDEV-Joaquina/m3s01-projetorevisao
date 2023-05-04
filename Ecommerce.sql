---------------------------------------
-- Criação de banco de dados
---------------------------------------
CREATE DATABASE ecommerce;


---------------------------------------
-- Criação de tabelas
---------------------------------------
CREATE TABLE customer (
  customer_id bigserial PRIMARY KEY,
  name varchar(255) NOT NULL,
  tax_id varchar(30) NOT NULL,
  email varchar(255) NOT NULL,
  phone varchar(20) NOT NULL,
  address varchar(255) NOT NULL,
  complement varchar(20),
  number varchar(6) NOT NULL,
  neighborhood varchar(30) NOT NULL,
  city varchar(40) NOT NULL,
  state varchar(20) NOT NULL
);

CREATE TABLE product (
 product_id bigserial PRIMARY KEY,
 name varchar(50) NOT NULL,
 description TEXT,
 price numeric(19,2) NOT NULL,
 qty_stock numeric(19,4) NOT NULL DEFAULT 0.00
);

CREATE TABLE payment_method (
  payment_method_id bigserial PRIMARY KEY,
  name varchar(20) NOT NULL,
  is_card boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE demand (
  demand_id bigserial PRIMARY KEY,
  date_ordered timestamp NOT NULL DEFAULT now(),
  customer_id bigint NOT NULL REFERENCES customer(customer_id),
  total_items numeric(19,2) NOT NULL,
  total numeric(19,2) NOT NULL
);

CREATE TABLE demand_item (
  demand_item_id bigserial PRIMARY KEY,
  demand_id  bigint NOT NULL REFERENCES demand(demand_id),
  product_id bigint NOT NULL REFERENCES product(product_id),
  price numeric(19,2) NOT NULL,
  qty   numeric(19,4) NOT NULL,
  total numeric(19,2) NOT NULL
);

CREATE TABLE demand_payment (
  demand_payment_id bigserial PRIMARY KEY,
  demand_id         bigint NOT NULL REFERENCES demand(demand_id),
  payment_method_id bigint NOT NULL REFERENCES payment_method(payment_method_id),
  card_number varchar(30),
  card_holder varchar(50),
  card_thru   varchar(7),
  card_verification int
);

CREATE TABLE cart (
  cart_id bigserial PRIMARY KEY,
  zipcode varchar(10),
  demand_id bigint REFERENCES demand(demand_id)
);

CREATE TABLE cart_item (
  cart_item_id bigserial PRIMARY KEY,
  cart_id    bigint NOT NULL REFERENCES cart(cart_id),
  product_id bigint NOT NULL REFERENCES product(product_id),
  qty numeric(19,4) NOT NULL
);
