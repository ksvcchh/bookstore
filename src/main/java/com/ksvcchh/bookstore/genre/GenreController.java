package com.ksvcchh.bookstore.genre;

import com.ksvcchh.bookstore.genre.dto.CreateGenreRequest;
import com.ksvcchh.bookstore.genre.dto.GenreDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService
                .findAll()
                .stream()
                .map(GenreMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GenreDto getGenreById(@PathVariable UUID id) {
        return GenreMapper.toDto(genreService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(
            @Valid @RequestBody CreateGenreRequest request
    ) {
        Genre createdGenre = genreService.create(request);
        return new ResponseEntity<>(GenreMapper.toDto(createdGenre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable UUID id) {
        genreService.delete(id);
    }
}