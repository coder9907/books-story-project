package com.example.booksstory.security;

import com.example.booksstory.entity.RefreshToken;
import com.example.booksstory.entity.User;
import com.example.booksstory.payload.AuthTokenPayload;
import com.example.booksstory.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenUtils {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${refresh-token.expired.time}")
    private Long refreshExpiredTimeMilly;

    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiredTime(new Date(new Date().getTime() + refreshExpiredTimeMilly));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;

    }

    public boolean validateRefreshToken(RefreshToken refreshToken) {
        return !refreshToken.getExpiredTime().after(new Date());
    }

    public RefreshToken refreshToken(AuthTokenPayload authTokenPayload) {
        RefreshToken refreshToken=refreshTokenRepository.findFirstByRefreshTokenOrderByCreatedAtDesc(authTokenPayload.getRefresh_token()).orElseThrow(()->new RuntimeException("refresh token not found"));
        if (validateRefreshToken(refreshToken)){
            throw new RuntimeException("refresh_token is expired");
        }
        User user=refreshToken.getUser();
        return refreshToken;
    }
}
