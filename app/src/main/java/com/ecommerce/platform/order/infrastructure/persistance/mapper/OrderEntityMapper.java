package com.ecommerce.platform.order.infrastructure.persistance.mapper;

import com.ecommerce.platform.order.domain.Order;
import com.ecommerce.platform.order.infrastructure.persistance.OrderEntity;

public interface OrderEntityMapper {
    Order toDomain(OrderEntity entity);

    OrderEntity toEntity(Order order);

    void updateEntity(Order domain, OrderEntity entity);
}
