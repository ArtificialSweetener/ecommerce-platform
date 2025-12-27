package com.ecommerce.platform.product.infrastructure.persistance.repository.impl;

import com.ecommerce.platform.product.domain.Product;
import com.ecommerce.platform.product.domain.ProductId;
import com.ecommerce.platform.product.domain.ProductRepository;
import com.ecommerce.platform.product.infrastructure.persistance.ProductEntity;
import com.ecommerce.platform.product.infrastructure.persistance.mapper.ProductEntityMapper;
import com.ecommerce.platform.product.infrastructure.persistance.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    @Transactional
    public void save(Product product) {
        ProductEntity entityToSave = productJpaRepository.findById(product.getId().id())
                .map(existingEntity -> {
                    mapper.updateEntity(product, existingEntity);
                    return existingEntity;
                })
                .orElseGet(() -> mapper.toEntity(product));
        productJpaRepository.save(entityToSave);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productJpaRepository.findById(productId.id())
                .map(mapper::toDomain);
    }
}
