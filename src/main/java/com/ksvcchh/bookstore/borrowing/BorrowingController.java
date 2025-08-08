package com.ksvcchh.bookstore.borrowing;

import com.ksvcchh.bookstore.borrowing.dto.BorrowingDto;
import com.ksvcchh.bookstore.borrowing.dto.CreateBorrowingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingDto> borrowBook(
            @Valid @RequestBody CreateBorrowingRequest request
    ) {
        Borrowing newBorrowing = borrowingService.borrowBook(
                request.getBookId(),
                request.getUserId()
        );
        return new ResponseEntity<>(BorrowingMapper.toDto(newBorrowing), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable UUID id) {
        Borrowing updatedBorrowing = borrowingService.returnBook(id);
        return ResponseEntity.ok(BorrowingMapper.toDto(updatedBorrowing));
    }
}