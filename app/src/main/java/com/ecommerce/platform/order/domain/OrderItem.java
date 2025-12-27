package com.ecommerce.platform.order.domain;

import com.ecommerce.platform.product.domain.ProductId;

import java.math.BigDecimal;

public record OrderItem(ProductId productId,
                        int quantity,
                        BigDecimal priceAtPurchase
) {
    public OrderItem {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (priceAtPurchase.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative");
    }

    public BigDecimal getSubtotal() {
        return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
    }
}
