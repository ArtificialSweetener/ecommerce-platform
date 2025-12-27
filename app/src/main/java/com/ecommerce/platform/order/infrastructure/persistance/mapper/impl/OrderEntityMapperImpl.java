package com.ecommerce.platform.order.infrastructure.persistance.mapper.impl;

import com.ecommerce.platform.order.domain.Order;
import com.ecommerce.platform.order.domain.OrderId;
import com.ecommerce.platform.order.domain.OrderItem;
import com.ecommerce.platform.order.infrastructure.persistance.OrderEntity;
import com.ecommerce.platform.order.infrastructure.persistance.OrderItemEntity;
import com.ecommerce.platform.order.infrastructure.persistance.mapper.OrderEntityMapper;
import com.ecommerce.platform.product.domain.ProductId;
import com.ecommerce.platform.user.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEntityMapperImpl implements OrderEntityMapper {

    @Override
    public Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        return Order.rehydrate(
                new OrderId(entity.getId()),
                new UserId(entity.getUserId()),
                mapToDomainItems(entity.getItems()),
                entity.getCreatedAt(),
                entity.getStatus()
        );
    }

    @Override
    public OrderEntity toEntity(Order domain) {
        if (domain == null) return null;

        return new OrderEntity(
                domain.getId().id(),
                domain.getUserId().id(),
                mapToEntityItems(domain.getItems()),
                domain.getStatus(),
                domain.getCreatedAt()
        );
    }

    @Override
    public void updateEntity(Order domain, OrderEntity entity) {
        if (domain == null || entity == null) return;

        entity.setStatus(domain.getStatus());
    }

    private List<OrderItemEntity> mapToEntityItems(List<OrderItem> domainItems) {
        return domainItems.stream()
                .map(item -> new OrderItemEntity(
                        item.productId().id(),
                        item.quantity(),
                        item.priceAtPurchase()
                ))
                .toList();
    }

    private List<OrderItem> mapToDomainItems(List<OrderItemEntity> entityItems) {
        return entityItems
                .stream()
                .map(itemEntity -> new OrderItem(
                        new ProductId(itemEntity.getProductId()),
                        itemEntity.getQuantity(),
                        itemEntity.getPriceAtPurchase()
                ))
                .toList();
    }
}