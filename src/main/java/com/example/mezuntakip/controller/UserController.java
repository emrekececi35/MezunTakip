package com.example.mezuntakip.controller;

import com.example.mezuntakip.dtos.request.UserDTO;
import com.example.mezuntakip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findFirstNameAndLastName(@RequestParam String FirstName,String LastName){
        System.out.println(userService.findFirstNameAndLastName(FirstName, LastName));
        return ResponseEntity.ok(userService.findFirstNameAndLastName(FirstName, LastName));
    }
    @GetMapping("/findGraduteYear")
    public  ResponseEntity<List<UserDTO>> findGraduteYear(@RequestParam String GraduteYear){
        System.out.println(userService.findGraduteYear(GraduteYear));
        return  ResponseEntity.ok(userService.findGraduteYear(GraduteYear));

    }

}
