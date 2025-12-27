package com.ecommerce.platform.order.domain;

import com.ecommerce.platform.user.domain.UserId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final OrderId id;
    private final UserId userId;
    private final List<OrderItem> items;
    private final LocalDateTime createdAt;
    private OrderStatus status;

    private Order(OrderId id, UserId userId, List<OrderItem> items, LocalDateTime createdAt, OrderStatus status) {
        this.id = Objects.requireNonNull(id, "OrderId is required");
        this.userId = Objects.requireNonNull(userId, "UserId is required");
        this.items = List.copyOf(Objects.requireNonNull(items, "Items list must not be null"));
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt is required");
        this.status = Objects.requireNonNull(status, "Status is required");

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
    }

    public static Order create(UserId userId, List<OrderItem> items) {
        return new Order(
                new OrderId(UUID.randomUUID()),
                userId,
                items,
                LocalDateTime.now(),
                OrderStatus.PENDING
        );
    }

    public static Order rehydrate(OrderId id, UserId userId, List<OrderItem> items, LocalDateTime createdAt, OrderStatus status) {
        return new Order(id, userId, items, createdAt, status);
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markAsShipped() {
        if (this.status != OrderStatus.PAID) {
            throw new IllegalStateException("Order must be PAID before it can be SHIPPED");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public OrderId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }
}