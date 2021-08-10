package com.example.mezuntakip.controller;


import com.example.mezuntakip.dtos.mapper.UserMapper;
import com.example.mezuntakip.dtos.request.LoginRequestDTO;
import com.example.mezuntakip.dtos.request.UserRequestDTO;
import com.example.mezuntakip.dtos.response.LoginResponseDTO;
import com.example.mezuntakip.model.User;
import com.example.mezuntakip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signUp(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.signUp(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(this.userService.login(loginRequestDTO));
    }
}
