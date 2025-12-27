package com.ecommerce.platform.product.infrastructure.persistance.mapper.impl;

import com.ecommerce.platform.product.domain.Product;
import com.ecommerce.platform.product.domain.ProductId;
import com.ecommerce.platform.product.infrastructure.persistance.ProductEntity;
import com.ecommerce.platform.product.infrastructure.persistance.mapper.ProductEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapperImpl implements ProductEntityMapper {

    @Override
    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return Product.rehydrate(
                new ProductId(entity.getId()),
                entity.getName(),
                entity.getPrice(),
                entity.getCreatedAt()
        );
    }

    @Override
    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId().id(),
                product.getName(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }

    @Override
    public void updateEntity(Product domain, ProductEntity entity) {
        entity.setName(domain.getName());
        entity.setPrice(domain.getPrice());
    }

}
