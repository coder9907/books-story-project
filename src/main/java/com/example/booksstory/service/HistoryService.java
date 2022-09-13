package com.example.booksstory.service;

import com.example.booksstory.payload.HistoryPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoryService {
    List<HistoryPayload> saveHistory(List<HistoryPayload> payloads);

    ResponseEntity<?> getByIdHistory(Long id);

    ResponseEntity<?> getAllHistory();

    ResponseEntity<?> getByHistoryBookId(Long bookId);
}
