package com.example.booksstory.controller;

import com.example.booksstory.entity.User;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.payload.UserPayload;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.security.SecurityUtils;
import com.example.booksstory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/me")
    public User getCurrentUser() {
        User user=userRepository.findByUsername(SecurityUtils.getCurrentUser().orElseThrow(() -> new ResourceNotFoundException("user not found")));
        return user;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserPayload userPayload){
        return userService.saveUser(userPayload);
    }

}
