package com.ecommerce.platform.user.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UserId id;
    private final String email;
    private final String password;
    private final LocalDateTime createdAt;

    private User(UserId id, String email, String password, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "UserId is required");
        this.email = validateEmail(email);
        this.password = validatePassword(password);
        this.createdAt = Objects.requireNonNull(createdAt, "CreatedAt is required");
    }

    public static User create(String email, String password) {
        return new User(new UserId(UUID.randomUUID()), email, password, LocalDateTime.now());
    }

    public static User rehydrate(UserId id, String email, String password, LocalDateTime createdAt) {
        return new User(id, email, password, createdAt);
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

}
