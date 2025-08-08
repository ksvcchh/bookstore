package com.ksvcchh.bookstore.author.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorRequest {
    @NotBlank(message = "Author name cannot be blank")
    private String name;

    @NotBlank(message = "Author surname cannot be blank")
    private String surname;

    @Email(message = "A valid email is required")
    @NotBlank
    private String email;
}