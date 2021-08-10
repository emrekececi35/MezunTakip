package com.example.mezuntakip.dtos.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private  String graduteYear;
    private String company;
}
