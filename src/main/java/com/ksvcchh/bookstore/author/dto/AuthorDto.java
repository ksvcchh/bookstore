package com.ksvcchh.bookstore.author.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
}