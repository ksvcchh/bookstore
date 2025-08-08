package com.ksvcchh.bookstore.genre.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GenreDto {
    private UUID id;
    private String name;
    private String description;
}