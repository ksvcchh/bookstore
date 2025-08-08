package com.ksvcchh.bookstore.user.dto;

import com.ksvcchh.bookstore.user.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private User.Role role;
    private LocalDate dateOfBirth;
}