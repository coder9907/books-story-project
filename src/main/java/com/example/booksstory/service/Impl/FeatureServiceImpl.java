package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Feature;
import com.example.booksstory.entity.FeatureGroup;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import com.example.booksstory.entity.translate.FeatureTranslate;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.FeatureGroupPayload;
import com.example.booksstory.payload.FeaturePayload;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.FeatureGroupRepository;
import com.example.booksstory.repository.FeatureRepository;
import com.example.booksstory.repository.translate.FeatureGroupTranslateRepository;
import com.example.booksstory.repository.translate.FeatureTranslateRepository;
import com.example.booksstory.service.FeatureGroupService;
import com.example.booksstory.service.FeatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final FeatureTranslateRepository featureTranslateRepository;
    private final BookRepository bookRepository;

    @Override
    public List<FeaturePayload> save(List<FeaturePayload> payloads, Long bookId) {
        try {
            Feature feature=new Feature();
            feature.setBooks(Arrays.asList(bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("book not found"))));
            featureRepository.save(feature);
            if (payloads.size() == LanguageEnum.values().length) {
                return payloads.stream().peek(featurePayload -> {
                    FeatureTranslate featureTranslate = new FeatureTranslate(featurePayload.getName(),feature,featurePayload.getLang());
                    featureTranslateRepository.save(featureTranslate);
                    featureTranslate.setId(featurePayload.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<FeaturePayload> getOne(Long id) {
        return featureTranslateRepository.getOneWithAllLang(id);
    }


    @Override
    public Page<FeaturePayload> getAll(int page, int size, LanguageEnum languageEnum) {
        return featureTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
    }

    @Override
    public ResponseEntity<?> deleteFeature(Long id) {
        try {
            featureRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error Feature Group",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FeaturePayload> editFeature(List<FeaturePayload> payloads) {
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                Feature feature = featureRepository.findById(payloads.get(0).getId()).orElseThrow(()->new ResourceNotFoundException("feature not found"));
                featureRepository.save(feature);
                return payloads.stream().peek(featurePayload -> {
                    FeatureTranslate featureTranslate = featureTranslateRepository.findById(featurePayload.getId()).orElseThrow(()->new ResourceNotFoundException("featuretranslate not found"));
                    featureTranslate.setFeature(feature);
                    featureTranslate.setLang(featurePayload.getLang());
                    featureTranslate.setName(featurePayload.getName());
                    featureTranslateRepository.save(featureTranslate);
                    featureTranslate.setId(feature.getId());
                }).collect(Collectors.toList());
            }
        }catch (Exception e){
            log.error("error Feature Group",e.getMessage());
        }
        return null;
    }
}
