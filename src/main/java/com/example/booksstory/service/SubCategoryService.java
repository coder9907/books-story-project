package com.example.booksstory.service;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.SubCategoryPayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryPayload> save(List<SubCategoryPayload> subCategoryPayloads);

    List<SubCategoryPayload> getOne(Long id);

    Page<SubCategoryPayload> getAll(int page, int size, LanguageEnum languageEnum);

    List<SubCategoryPayload> getBySubCategoryCategoryId(int page, int size, LanguageEnum languageEnum, Long categoryId);

    List<SubCategoryPayload> editSubCategory(List<SubCategoryPayload> payloads);

    ResponseEntity<?> deleteSubcategory(Long id);
}
