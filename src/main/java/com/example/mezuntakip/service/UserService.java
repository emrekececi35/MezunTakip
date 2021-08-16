package com.example.mezuntakip.service;


import com.example.mezuntakip.dtos.mapper.UserDtoConvert;
import com.example.mezuntakip.dtos.request.LoginRequestDTO;
import com.example.mezuntakip.dtos.request.UserDTO;
import com.example.mezuntakip.dtos.response.LoginResponseDTO;
import com.example.mezuntakip.exceptions.InvalidCredentialsException;
import com.example.mezuntakip.exceptions.UniqueViolationException;
import com.example.mezuntakip.model.User;
import com.example.mezuntakip.repository.UserRepository;
import com.example.mezuntakip.utils.security.JWTUtils;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder encoder;
    private JWTUtils jwtUtils;
    private  UserDtoConvert userDtoConvert;

    @Autowired
    public UserService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            BCryptPasswordEncoder encoder,
            JWTUtils jwtUtils,
            UserDtoConvert userDtoConvert) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userDtoConvert = userDtoConvert;
    }

    public LoginResponseDTO login(LoginRequestDTO login) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        User user = this.userRepository
                .findUserByEmail(login.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        String token = this.jwtUtils.generateToken(user);

        return new LoginResponseDTO(login.getEmail(), token);
    }

    public LoginResponseDTO signUp(User user) {

        if( this.userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UniqueViolationException("Email");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        this.userRepository.save(user);

        String token = this.jwtUtils.generateToken(user);

        return new LoginResponseDTO(user.getEmail(), token);
    }

    public List <UserDTO> getAllUsers () {
        List<User> userList = userRepository.findAll ();
        List <UserDTO> userDtoList = new ArrayList<>();

        for (User user : userList) {

            userDtoList.add (userDtoConvert.convert (user));
        }
        return userDtoList;
    }

    public List <UserDTO> findFirstNameAndLastName(String FirstName, String LastName) {
        List<User> userList = userRepository.findByFirstNameAndAndLastName (FirstName, LastName);
        List <UserDTO> userDtoList = new ArrayList<>();

        for (User user : userList) {

            userDtoList.add (userDtoConvert.convert (user));
        }
        return userDtoList;
    }

    public List <UserDTO> findGraduteYear(String GraduteYear) {
        List<User> userList = userRepository.findByGraduteYear (GraduteYear);
        List <UserDTO> userDtoList = new ArrayList<>();

        for (User user : userList) {

            userDtoList.add (userDtoConvert.convert (user));
        }
        return userDtoList;
    }
}
