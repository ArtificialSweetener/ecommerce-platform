package com.ecommerce.platform.user.infrastructure.persistance.mapper;

import com.ecommerce.platform.user.domain.User;
import com.ecommerce.platform.user.infrastructure.persistance.UserEntity;

public interface UserEntityMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
    void updateEntity(User domain, UserEntity entity);
}
