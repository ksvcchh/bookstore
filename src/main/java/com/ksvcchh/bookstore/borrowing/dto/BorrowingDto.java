package com.ksvcchh.bookstore.borrowing.dto;

import com.ksvcchh.bookstore.book.dto.BookDto;
import com.ksvcchh.bookstore.user.dto.UserDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class BorrowingDto {
    private UUID id;
    private Instant borrowDate;
    private Instant dueDate;
    private Instant returnDate;
    private BigDecimal fineAmount;
    private UserDto user;
    private BookDto book;
}