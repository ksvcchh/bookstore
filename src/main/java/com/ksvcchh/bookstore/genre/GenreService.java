package com.ksvcchh.bookstore.genre;

import com.ksvcchh.bookstore.exception.ResourceNotFoundException;
import com.ksvcchh.bookstore.genre.dto.CreateGenreRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(UUID id) {
        return genreRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

    @Transactional
    public Genre create(CreateGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        genre.setDescription(request.getDescription());
        return genreRepository.save(genre);
    }

    @Transactional
    public void delete(UUID id) {
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre not found with id: " + id);
        }
        genreRepository.deleteById(id);
    }
}