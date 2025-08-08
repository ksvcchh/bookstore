package com.ksvcchh.bookstore.book;

import com.ksvcchh.bookstore.author.AuthorMapper;
import com.ksvcchh.bookstore.book.dto.BookDto;
import com.ksvcchh.bookstore.genre.GenreMapper;
import com.ksvcchh.bookstore.borrowing.Borrowing;

import java.util.stream.Collectors;

public class BookMapper {

    public static BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setPublisher(book.getPublisher());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setCoverImage(book.getCoverImage());
        dto.setCreatedAt(book.getCreatedAt());
        dto.setUpdatedAt(book.getUpdatedAt());

        if (book.getAuthors() != null) {
            dto.setAuthors(
                    book.getAuthors()
                            .stream()
                            .map(AuthorMapper::toDto)
                            .collect(Collectors.toSet())
            );
        }

        if (book.getGenres() != null) {
            dto.setGenres(
                    book.getGenres()
                            .stream()
                            .map(GenreMapper::toDto)
                            .collect(Collectors.toSet())
            );
        }

        long borrowedCount = book.getBorrowings() == null
                ? 0
                : book.getBorrowings()
                .stream()
                .filter(b -> b.getReturnDate() == null)
                .count();
        dto.setAvailableCopies((int) (book.getTotalCopies() - borrowedCount));

        return dto;
    }
}