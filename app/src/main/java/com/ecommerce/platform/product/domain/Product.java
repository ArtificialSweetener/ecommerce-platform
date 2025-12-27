package com.ecommerce.platform.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final ProductId id;
    private final String name;
    private final BigDecimal price;
    private final LocalDateTime createdAt;

    private Product(ProductId id, String name, BigDecimal price, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "ProductId is required");
        this.name = validateName(name);
        this.price = validatePrice(price);
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt is required");
    }

    public static Product create(String name, BigDecimal price) {
        return new Product(new ProductId(UUID.randomUUID()), name, price, LocalDateTime.now());
    }

    public static Product rehydrate(ProductId id, String name, BigDecimal price, LocalDateTime createdAt) {
        return new Product(id, name, price, createdAt);
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        return name;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Price must be zero or positive");
        }
        return price;
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
