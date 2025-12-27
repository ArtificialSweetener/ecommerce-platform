CREATE SCHEMA IF NOT EXISTS order_db;

CREATE TABLE order_db.orders
(
    id         UUID PRIMARY KEY,
    user_id    UUID        NOT NULL,
    status     VARCHAR(50) NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT now()
);

CREATE INDEX idx_orders_user_id ON order_db.orders (user_id);

CREATE TABLE order_db.order_items
(
    order_id   UUID           NOT NULL,
    product_id UUID           NOT NULL,
    quantity   INTEGER        NOT NULL,
    price_at_purchase      DECIMAL(19, 2) NOT NULL,

    PRIMARY KEY (order_id, product_id),

    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
            REFERENCES order_db.orders (id) ON DELETE CASCADE
);

CREATE INDEX idx_order_items_product_id ON order_db.order_items (product_id);