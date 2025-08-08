package com.ksvcchh.bookstore.borrowing;

import com.ksvcchh.bookstore.book.BookMapper;
import com.ksvcchh.bookstore.borrowing.dto.BorrowingDto;
import com.ksvcchh.bookstore.user.UserMapper;

public class BorrowingMapper {

    public static BorrowingDto toDto(Borrowing borrowing) {
        BorrowingDto dto = new BorrowingDto();
        dto.setId(borrowing.getId());
        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setDueDate(borrowing.getDueDate());
        dto.setReturnDate(borrowing.getReturnDate());
        dto.setFineAmount(borrowing.getFineAmount());
        dto.setUser(UserMapper.toDto(borrowing.getUser()));
        dto.setBook(BookMapper.toDto(borrowing.getBook()));
        return dto;
    }
}