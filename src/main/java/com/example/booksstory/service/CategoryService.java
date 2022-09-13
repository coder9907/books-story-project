package com.example.booksstory.service;

import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.CategoryPayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryPayload> save(List<CategoryPayload> payloads);

    List<CategoryPayload> getOne(Long id);

    Page<CategoryPayload> getAll(int page, int size, LanguageEnum languageEnum);

    ResponseEntity<?> deleteByIdCategory(List<CategoryPayload> payloads);

    List<CategoryPayload> editCategory(List<CategoryPayload> payloads);
}
