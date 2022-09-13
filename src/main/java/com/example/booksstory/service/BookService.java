package com.example.booksstory.service;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.BookPayload;
import com.example.booksstory.payload.CategoryPayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    List<BookPayload> save(List<BookPayload> payloads, Long subCategoryId);

    List<BookPayload> getOne(Long id);

    Page<BookPayload> getAll(int page, int size, LanguageEnum languageEnum);

    ResponseEntity<?> getAllBooks();

    ResponseEntity<?> findBySubCategoryId(int page, int size, LanguageEnum languageEnum, Long id);

    List<BookPayload> editBook(List<BookPayload> payloads, Long subCategoryId);

    ResponseEntity<?> deleteBook(Long id);
}
