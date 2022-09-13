package com.example.booksstory.controller;

import com.example.booksstory.model.Result;
import com.example.booksstory.payload.AuthTokenPayload;
import com.example.booksstory.payload.UserPayload;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.security.AuthService;
import com.example.booksstory.security.JwtTokenProvider;
import com.example.booksstory.security.RefreshTokenUtils;
import com.example.booksstory.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserPayload payload) {
        try {
            return ResponseEntity.ok(authService.createToken(payload));
        } catch (Exception e) {
            log.error("error in login - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody AuthTokenPayload payload) {
        try {
            return ResponseEntity.ok(authService.refreshToken(payload));
        } catch (Exception e) {
            log.error("error in refresh token - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(e.getMessage()));
        }
    }

}
