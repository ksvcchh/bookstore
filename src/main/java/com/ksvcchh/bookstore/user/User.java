package com.ksvcchh.bookstore.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String surname;

    @Column(length = 70, nullable = false, unique = true)
    private String email;

    @Column(length = 72, nullable = false)
    private String password;

    @Column(length = 50)
    private String city;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "dob")
    private Instant dateOfBirth;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public enum Role {
        USER,
        MODERATOR,
        ADMIN
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}