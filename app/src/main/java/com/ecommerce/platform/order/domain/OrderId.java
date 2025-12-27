package com.ecommerce.platform.order.domain;

import java.util.Objects;
import java.util.UUID;

public record OrderId (UUID id){
    public OrderId {
        Objects.requireNonNull(id,"UUID must not be null");
    }
}
