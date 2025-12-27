package com.ecommerce.platform.user.infrastructure.persistance.repository.impl;

import com.ecommerce.platform.user.domain.User;
import com.ecommerce.platform.user.domain.UserId;
import com.ecommerce.platform.user.domain.UserRepository;
import com.ecommerce.platform.user.infrastructure.persistance.UserEntity;
import com.ecommerce.platform.user.infrastructure.persistance.mapper.UserEntityMapper;
import com.ecommerce.platform.user.infrastructure.persistance.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper mapper;

    @Override
    @Transactional
    public void save(User user) {
        UserEntity entityToSave = userJpaRepository.findById(user.getId().id())
                .map(existingEntity -> {
                    mapper.updateEntity(user, existingEntity);
                    return existingEntity;
                })
                .orElseGet(() -> mapper.toEntity(user));
        userJpaRepository.save(entityToSave);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.id())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }
}
