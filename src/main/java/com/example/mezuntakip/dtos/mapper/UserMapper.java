package com.example.mezuntakip.dtos.mapper;



import com.example.mezuntakip.dtos.request.UserRequestDTO;
import com.example.mezuntakip.model.User;
import com.example.mezuntakip.model.enums.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        return new User(
                userRequestDTO.getFirstName(),
                userRequestDTO.getLastName(),
                userRequestDTO.getGraduteYear(),
                userRequestDTO.getPassword(),
                userRequestDTO.getEmail(),
                Role.toEnum(userRequestDTO.getRole())
        );
    }

    public static List<User> toEntity(List<UserRequestDTO> userRequestDTOS) {
        return userRequestDTOS.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
    }
}
