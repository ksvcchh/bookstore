package com.ksvcchh.bookstore.book;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
class Book {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String surname;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    private String city;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate dob;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Book() {}

    public enum Role {
        User,
        Moderator,
        Admin
    }

}