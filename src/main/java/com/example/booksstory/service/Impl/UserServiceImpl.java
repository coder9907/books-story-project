package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.User;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.UserPayload;
import com.example.booksstory.repository.RoleRepository;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> saveUser(@RequestBody UserPayload userPayload){

        try {
            User user=userRepository.findByUsername(userPayload.getUsername());

            if (user == null){

                User user1=new User();
                user1.setUsername(userPayload.getUsername());
                user1.setPassword(passwordEncoder.encode(userPayload.getPassword()));
                user1.setPhone(userPayload.getPhone());
                user1.setIsAdmin(false);
                user1.setEmail(userPayload.getEmail());
                user1.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

                user1=userRepository.save(user1);

                if (user1 != null){
                    return ResponseEntity.ok(new Result(true,"save user succesfull",user));
                }
                return new ResponseEntity(new Result(false,"save error user",null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(new Result(false,"Bu username oldin royhatdan otgan",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("user save not found",e.getMessage());
            return new ResponseEntity(new Result(false,"save error user",null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserPayload> editUser(List<UserPayload> payloads){
        try {
            if (payloads.size()>=0 && payloads != null){
                payloads.stream().peek(userPayload -> {
                    User user=userRepository.findById(userPayload.getId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
                    if (user != null){
                        user.setUsername(userPayload.getUsername());
                        user.setPhone(userPayload.getPhone());
                        user.setPassword(passwordEncoder.encode(userPayload.getPassword()));
                        user.setEmail(userPayload.getEmail());
                        userRepository.save(user);
                    }
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("edit user error",e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> delete(List<UserPayload> payloads){
        try {
            if (payloads != null && payloads.size()>=0){
                payloads.stream().peek(userPayload -> {
                    userRepository.deleteById(userPayload.getId());
                }).collect(Collectors.toList());
                return ResponseEntity.ok(new Result(true,"delete succesfull",null));
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("delete error in user",e.getMessage());
            return new ResponseEntity(new Result(false,"no succesful",null),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<User> getAllUser(){
        try {
            List<User> users=new ArrayList<>();
            users=userRepository.findAll();
            if (users != null) {
                return users;
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("getAllUser error",e.getMessage());
        }
        return null;
    }

}
