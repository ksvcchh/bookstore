package com.ksvcchh.bookstore.user;

import com.ksvcchh.bookstore.user.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setCity(user.getCity());
        dto.setRole(user.getRole());
        dto.setDateOfBirth(user.getDateOfBirth());
        return dto;
    }
}