package com.ksvcchh.bookstore.book;

import com.ksvcchh.bookstore.author.Author;
import com.ksvcchh.bookstore.author.AuthorRepository;
import com.ksvcchh.bookstore.book.dto.CreateBookRequest;
import com.ksvcchh.bookstore.exception.ResourceNotFoundException;
import com.ksvcchh.bookstore.genre.Genre;
import com.ksvcchh.bookstore.genre.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(java.util.UUID id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public Book createBook(CreateBookRequest request) {
        Book book = new Book();
        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setPublicationDate(request.getPublicationDate());
        book.setPublisher(request.getPublisher());
        book.setTotalCopies(request.getTotalCopies());
        book.setCoverImage(request.getCoverImage());

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(request.getAuthorIds()));
        if (authors.size() != request.getAuthorIds().size()) {
            throw new ResourceNotFoundException("One or more authors not found.");
        }
        book.setAuthors(authors);

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(request.getGenreIds()));
        if (genres.size() != request.getGenreIds().size()) {
            throw new ResourceNotFoundException("One or more genres not found.");
        }
        book.setGenres(genres);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(java.util.UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}