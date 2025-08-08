package com.ksvcchh.bookstore.author;

import com.ksvcchh.bookstore.author.dto.AuthorDto;
import com.ksvcchh.bookstore.author.dto.CreateAuthorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService
                .findAll()
                .stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable UUID id) {
        return AuthorMapper.toDto(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(
            @Valid @RequestBody CreateAuthorRequest request
    ) {
        Author createdAuthor = authorService.create(request);
        return new ResponseEntity<>(AuthorMapper.toDto(createdAuthor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.delete(id);
    }
}