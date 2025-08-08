package com.ksvcchh.bookstore.book.dto;

import com.ksvcchh.bookstore.author.dto.AuthorDto;
import com.ksvcchh.bookstore.genre.dto.GenreDto;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class BookDto {
    private UUID id;
    private String isbn;
    private String title;
    private LocalDate publicationDate;
    private String publisher;
    private int totalCopies;
    private int availableCopies;
    private String coverImage;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
    private Instant createdAt;
    private Instant updatedAt;
}