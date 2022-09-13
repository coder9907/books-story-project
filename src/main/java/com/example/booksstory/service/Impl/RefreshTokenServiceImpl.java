package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.User;
import com.example.booksstory.security.SecurityUtils;
import com.example.booksstory.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final SecurityUtils securityUtils;

    @Override
    public User getByTokenUser() {
        String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
        User user = new User();
        user.setUsername(username);
        return user;
    }

}
