package com.example.mezuntakip.dtos.mapper;

import com.example.mezuntakip.dtos.request.UserDTO;
import com.example.mezuntakip.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConvert {

    public UserDTO convert (User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .graduteYear(user.getGraduteYear())
                .company(user.getCompany())
                .build();
    }
}
