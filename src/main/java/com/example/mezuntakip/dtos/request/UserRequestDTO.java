package com.example.mezuntakip.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotNull(message = "Insert firstName")
    @Size(min = 1, message = "The field 'firstName' must have at least 1 character")
    private String firstName;

    @NotNull(message = "Insert lastName")
    @Size(min = 1, message = "The field 'lastName' must have at least 1 character")
    private String lastName;

    @NotNull(message = "Insert graduteYear")
    @Size(max= 5)
    private  String graduteYear;

    @NotNull(message = "insert password")
    @Size(min = 8, max = 20, message = "The field 'password' must have at least 4 characters and at maximum 12")
    private String password;


    @NotNull(message = "insert email")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Invalid email format")
    private String email;

    private String role;
    private String company;
}
