package com.ksvcchh.bookstore.borrowing.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateBorrowingRequest {
    @NotNull
    private UUID bookId;
    @NotNull
    private UUID userId;
}