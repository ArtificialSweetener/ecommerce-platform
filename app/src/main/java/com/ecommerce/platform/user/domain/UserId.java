package com.ecommerce.platform.user.domain;

import java.util.Objects;
import java.util.UUID;

public record UserId(UUID id) {

    public UserId {
        Objects.requireNonNull(id, "UUID must not be null");
    }
}
