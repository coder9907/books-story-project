package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Autor;
import com.example.booksstory.entity.History;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.AutorPayload;
import com.example.booksstory.payload.HistoryPayload;
import com.example.booksstory.repository.AutorRepository;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.HistoryRepository;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.service.AutorService;
import com.example.booksstory.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<HistoryPayload> saveHistory(List<HistoryPayload> payloads) {
        try {
            if (payloads != null && payloads.size() >= 0) {
                return payloads.stream().peek(historyPayload -> {
                    History history = new History();
                    history.setUsers(Arrays.asList(userRepository.findById(historyPayload.getUserID()).orElseThrow(() -> new ResourceNotFoundException("user not found"))));
                    history.setBooks(Arrays.asList(bookRepository.findById(historyPayload.getBookId()).orElseThrow(() -> new ResourceNotFoundException("book not found"))));
                    historyRepository.save(history);
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error("save not found in history", e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getByIdHistory(Long id) {
        try {
            History history = historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("history not found"));
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            log.error("error history", e.getMessage());
            return new ResponseEntity(new Result(false, "error getByIdHistory", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllHistory() {
        try {
            List<History> histories = historyRepository.findAll();
            return ResponseEntity.ok(histories);
        } catch (Exception e) {
            log.error("error history", e.getMessage());
            return new ResponseEntity(new Result(false, "error getByIdHistory", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getByHistoryBookId(Long bookId) {
        try {
            List<HistoryPayload> historyPayloads = historyRepository.getAllByHistoryBookId(bookId);
            return ResponseEntity.ok(historyPayloads);
        } catch (Exception e) {
            log.error("error history", e.getMessage());
            return new ResponseEntity(new Result(false, "error getByBookId", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getByHistoryUserId(Long userId) {
        try {
            List<HistoryPayload> historyPayloads = historyRepository.getAllByHistoryUserId(userId);
            return ResponseEntity.ok(historyPayloads);
        } catch (Exception e) {
            log.error("error history", e.getMessage());
            return new ResponseEntity(new Result(false, "error getByBookId", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
