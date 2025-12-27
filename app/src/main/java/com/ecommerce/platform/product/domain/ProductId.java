package com.ecommerce.platform.product.domain;

import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID id) {

    public ProductId {
        Objects.requireNonNull(id, "UUID must not be null");
    }
}
