package com.ecommerce.platform.user.domain;

import java.time.LocalDateTime;
import java.util.*;

public class User {

    private final UserId id;
    private final String email;
    private final String password;
    private final UserStatus status;
    private final Set<UserRole> roles;
    private final LocalDateTime createdAt;


    private User(UserId id, String email, String password, UserStatus status, Set<UserRole> roles, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "UserId is required");
        this.email = validateEmail(email);
        this.password = validatePassword(password);
        this.status = Objects.requireNonNull(status, "Status is required");
        this.roles = new HashSet<>(roles);
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt is required");
    }

    public static User create(String email, String password, Set<UserRole> roles) {

        return new User(
                new UserId(UUID.randomUUID()),
                email,
                password, UserStatus.ACTIVE,
                roles.isEmpty() ? Set.of(UserRole.CUSTOMER) : roles,
                LocalDateTime.now()
        );
    }

    public static User rehydrate(UserId id,
                                 String email,
                                 String password,
                                 UserStatus status,
                                 Set<UserRole> roles,
                                 LocalDateTime createdAt
    ) {

        return new User(id, email, password, status, roles, createdAt);
    }

    private String validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        return email;
    }

    private String validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
        return password;
    }

    public UserId getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<UserRole> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public UserStatus getStatus() {
        return status;
    }

}
