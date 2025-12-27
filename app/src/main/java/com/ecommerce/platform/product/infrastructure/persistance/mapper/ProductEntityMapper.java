package com.ecommerce.platform.product.infrastructure.persistance.mapper;

import com.ecommerce.platform.product.domain.Product;
import com.ecommerce.platform.product.infrastructure.persistance.ProductEntity;

public interface ProductEntityMapper {
    Product toDomain(ProductEntity productEntity);

    ProductEntity toEntity(Product product);

    void updateEntity(Product domain, ProductEntity entity);
}
