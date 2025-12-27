package com.ecommerce.platform.order.domain;

import com.ecommerce.platform.user.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(OrderId id);
    List<Order> findByUserId(UserId userId);
    List<Order> findAll();
}
