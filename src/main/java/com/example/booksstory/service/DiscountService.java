package com.example.booksstory.service;

import com.example.booksstory.payload.DiscountPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiscountService {
    List<DiscountPayload> save(List<DiscountPayload> payloads);

    ResponseEntity<?> editDiscount(DiscountPayload discountPayload, Long id);

    ResponseEntity<?> getAllDiscount();

    ResponseEntity<?> deleteDiscount(List<DiscountPayload> payloads);

    ResponseEntity<?> getByIdDiscount(Long id);
}
