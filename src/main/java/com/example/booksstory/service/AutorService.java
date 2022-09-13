package com.example.booksstory.service;

import com.example.booksstory.payload.AutorPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AutorService {
    List<AutorPayload> save(List<AutorPayload> payloads);

    ResponseEntity<?> editAutor(AutorPayload autorPayload);

    ResponseEntity<?> deleteAutor(Long id);

    ResponseEntity<?> getAllAutor();

    ResponseEntity<?> findByIdAutor(Long id);
}
