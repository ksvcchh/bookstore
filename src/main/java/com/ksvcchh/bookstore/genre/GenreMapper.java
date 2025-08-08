package com.ksvcchh.bookstore.genre;

import com.ksvcchh.bookstore.genre.dto.GenreDto;

public class GenreMapper {

    public static GenreDto toDto(Genre genre) {
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }
}