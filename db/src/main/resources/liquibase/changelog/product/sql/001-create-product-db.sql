CREATE SCHEMA IF NOT EXISTS product_db;

CREATE TABLE product_db.products
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(255)   NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT now()
);
