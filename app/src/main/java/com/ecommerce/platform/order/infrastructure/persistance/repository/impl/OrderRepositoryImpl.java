package com.ecommerce.platform.order.infrastructure.persistance.repository.impl;

import com.ecommerce.platform.order.domain.Order;
import com.ecommerce.platform.order.domain.OrderId;
import com.ecommerce.platform.order.domain.OrderRepository;
import com.ecommerce.platform.order.infrastructure.persistance.OrderEntity;
import com.ecommerce.platform.order.infrastructure.persistance.mapper.OrderEntityMapper;
import com.ecommerce.platform.order.infrastructure.persistance.repository.OrderJpaRepository;
import com.ecommerce.platform.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository jpaRepository;
    private final OrderEntityMapper mapper;

    @Override
    @Transactional
    public void save(Order order) {
        OrderEntity entityToSave = jpaRepository.findById(order.getId().id())
                .map(existingEntity -> {
                    mapper.updateEntity(order, existingEntity);
                    return existingEntity;
                })
                .orElseGet(() -> mapper.toEntity(order));

        jpaRepository.save(entityToSave);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(OrderId orderId) {
        return jpaRepository.findById(orderId.id())
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByUserId(UserId userId) {
        return jpaRepository.findByUserId(userId.id())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
