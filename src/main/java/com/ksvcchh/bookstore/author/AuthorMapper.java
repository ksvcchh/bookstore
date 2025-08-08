package com.ksvcchh.bookstore.author;

import com.ksvcchh.bookstore.author.dto.AuthorDto;

public class AuthorMapper {

    public static AuthorDto toDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        dto.setEmail(author.getEmail());
        return dto;
    }
}