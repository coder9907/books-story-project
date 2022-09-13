package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPayload {

    private Long id;

    private String username;

    public UserPayload(String username) {
        this.username = username;
    }

    private String phone;

    private String password;

    private String email;

    private Boolean isAdmin;

    public UserPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserPayload(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public UserPayload(Long id, String username, String phone, String email, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
