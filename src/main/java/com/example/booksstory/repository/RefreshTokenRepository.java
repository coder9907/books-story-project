package com.example.booksstory.repository;

import com.example.booksstory.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findFirstByRefreshTokenOrderByCreatedAtDesc(String refreshToken);

}
