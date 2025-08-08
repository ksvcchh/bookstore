package com.ksvcchh.bookstore.genre.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGenreRequest {
    @NotBlank(message = "Genre name cannot be blank")
    private String name;
    private String description;
}