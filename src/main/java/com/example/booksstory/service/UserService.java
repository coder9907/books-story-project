package com.example.booksstory.service;

import com.example.booksstory.entity.User;
import com.example.booksstory.payload.UserPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    ResponseEntity<?> saveUser(@RequestBody UserPayload userPayload);

    List<UserPayload> editUser(List<UserPayload> payloads);

    ResponseEntity<?> delete(List<UserPayload> payloads);

    List<User> getAllUser();
}
