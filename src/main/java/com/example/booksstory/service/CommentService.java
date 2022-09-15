package com.example.booksstory.service;

import com.example.booksstory.payload.CommentPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<CommentPayload> save(List<CommentPayload> payloads);

    ResponseEntity<?> getByIdComment(Long commentId);

    ResponseEntity<?> getAllComment();

    ResponseEntity<?> findByBookId(Long bookId);

    ResponseEntity<?> findByUserId(Long userId);

    ResponseEntity<?> deleteComment(Long commentId);

    ResponseEntity<?> editComment(CommentPayload commentPayload);
}
