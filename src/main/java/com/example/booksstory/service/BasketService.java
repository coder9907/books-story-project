package com.example.booksstory.service;

import com.example.booksstory.payload.BasketPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BasketService {
    List<BasketPayload> saveBasket(List<BasketPayload> payloads);

    ResponseEntity<?> deleteBasket(List<BasketPayload> payloads);
}
