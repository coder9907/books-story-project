package com.example.booksstory.security;

import com.example.booksstory.entity.User;
import com.example.booksstory.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component("auditorAware")
@RequiredArgsConstructor
public class SecurityAuditorResolver implements AuditorAware<User> {
    private final EntityManager entityManager;
    private  final RefreshTokenService refreshTokenService;

    @Override
    public Optional<User> getCurrentAuditor() {
        User user=refreshTokenService.getByTokenUser();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }


}