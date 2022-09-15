package com.example.booksstory.service;

import com.example.booksstory.payload.FavoritePayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteService {
    ResponseEntity<?> saveFavorite(List<FavoritePayload> payloads);

    ResponseEntity<?> deleteFavorite(Long id);

    ResponseEntity<?> getByFavoriteUserId(Long userId);

    ResponseEntity<?> getByFavoriteBookId(Long bookId);

    ResponseEntity<?> getAllFavourite();
}
