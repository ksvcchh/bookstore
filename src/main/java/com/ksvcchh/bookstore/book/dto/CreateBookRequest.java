package com.ksvcchh.bookstore.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class CreateBookRequest {
    @NotBlank
    private String isbn;
    @NotBlank
    private String title;
    private LocalDate publicationDate;
    private String publisher;
    @NotNull
    @Positive
    private Integer totalCopies;
    private String coverImage;
    @NotEmpty
    private Set<UUID> authorIds;
    @NotEmpty
    private Set<UUID> genreIds;
}