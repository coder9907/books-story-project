package com.example.booksstory.service;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.FeatureGroupPayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeatureGroupService {
    List<FeatureGroupPayload> save(List<FeatureGroupPayload> payloads);

    List<FeatureGroupPayload> getOne(Long id);

    Page<FeatureGroupPayload> getAll(int page, int size, LanguageEnum languageEnum);

    ResponseEntity<?> deleteFeatureGroup(Long id);

    List<FeatureGroupPayload> editFeatureGroup(List<FeatureGroupPayload> payloads);
}
