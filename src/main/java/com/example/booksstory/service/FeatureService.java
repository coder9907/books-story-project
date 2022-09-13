package com.example.booksstory.service;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.FeatureGroupPayload;
import com.example.booksstory.payload.FeaturePayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeatureService {
    List<FeaturePayload> save(List<FeaturePayload> payloads, Long bookId);

    List<FeaturePayload> getOne(Long id);

    Page<FeaturePayload> getAll(int page, int size, LanguageEnum languageEnum);

    ResponseEntity<?> deleteFeature(Long id);

    List<FeaturePayload> editFeature(List<FeaturePayload> payloads);
}
