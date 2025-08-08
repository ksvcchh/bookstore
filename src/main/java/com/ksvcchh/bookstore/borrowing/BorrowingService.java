package com.ksvcchh.bookstore.borrowing;

import com.ksvcchh.bookstore.book.Book;
import com.ksvcchh.bookstore.book.BookRepository;
import com.ksvcchh.bookstore.book.BookMapper;
import com.ksvcchh.bookstore.exception.ResourceNotFoundException;
import com.ksvcchh.bookstore.user.User;
import com.ksvcchh.bookstore.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Borrowing borrowBook(UUID bookId, UUID userId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        int availableCopies = BookMapper.toDto(book).getAvailableCopies();
        if (availableCopies <= 0) {
            throw new IllegalStateException("No copies of the book are available for borrowing.");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setUser(user);
        borrowing.setBorrowDate(Instant.now());
        borrowing.setDueDate(Instant.now().plus(14, ChronoUnit.DAYS));

        return borrowingRepository.save(borrowing);
    }

    @Transactional
    public Borrowing returnBook(UUID borrowingId) {
        Borrowing borrowing = borrowingRepository
                .findById(borrowingId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found"));

        if (borrowing.getReturnDate() != null) {
            throw new IllegalStateException("Book has already been returned.");
        }

        borrowing.setReturnDate(Instant.now());

        if (borrowing.getReturnDate().isAfter(borrowing.getDueDate())) {
            borrowing.setFineAmount(new java.math.BigDecimal("5.00"));
        }

        return borrowingRepository.save(borrowing);
    }
}