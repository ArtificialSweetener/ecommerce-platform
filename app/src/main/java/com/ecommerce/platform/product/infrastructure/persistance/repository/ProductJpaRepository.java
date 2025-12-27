package com.ecommerce.platform.product.infrastructure.persistance.repository;

import com.ecommerce.platform.product.infrastructure.persistance.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
}
