package com.example.mezuntakip.controller;

import com.example.mezuntakip.dtos.request.UserDTO;
import com.example.mezuntakip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        System.out.println(userService.getAllUsers());
        return ResponseEntity.ok (userService.getAllUsers ());
    }
}
