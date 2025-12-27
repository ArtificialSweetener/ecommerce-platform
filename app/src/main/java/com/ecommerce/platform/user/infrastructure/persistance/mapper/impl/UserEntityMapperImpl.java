package com.ecommerce.platform.user.infrastructure.persistance.mapper.impl;

import com.ecommerce.platform.user.domain.User;
import com.ecommerce.platform.user.domain.UserId;
import com.ecommerce.platform.user.infrastructure.persistance.UserEntity;
import com.ecommerce.platform.user.infrastructure.persistance.mapper.UserEntityMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserEntityMapperImpl implements UserEntityMapper {
    @Override
    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.rehydrate(
                new UserId(entity.getId()),
                entity.getEmail(),
                entity.getPassword(),
                entity.getStatus(),
                entity.getRoles(),
                entity.getCreatedAt()
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().id(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getRoles(),
                user.getCreatedAt()
        );
    }

    @Override
    public void updateEntity(User domain, UserEntity entity) {
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setStatus(domain.getStatus());
        entity.setRoles(new HashSet<>(domain.getRoles()));
    }
}
