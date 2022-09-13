package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.FeatureGroupPayload;
import com.example.booksstory.repository.FeatureGroupRepository;
import com.example.booksstory.repository.translate.FeatureGroupTranslateRepository;
import com.example.booksstory.entity.FeatureGroup;
import com.example.booksstory.service.FeatureGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeatureGroupServiceImpl implements FeatureGroupService {

    private final FeatureGroupRepository featureGroupRepository;
    private final FeatureGroupTranslateRepository featureGroupTranslateRepository;

    @Override
    public List<FeatureGroupPayload> save(List<FeatureGroupPayload> payloads) {
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                FeatureGroup xususiyatGroup = new FeatureGroup();
                featureGroupRepository.save(xususiyatGroup);
                return payloads.stream().peek(featureGroupTranslate -> {
                    FeatureGroupTranslate featureGroupTranslate1 = new FeatureGroupTranslate(featureGroupTranslate.getName(),xususiyatGroup,featureGroupTranslate.getLang());
                    featureGroupTranslateRepository.save(featureGroupTranslate1);
                    featureGroupTranslate.setId(xususiyatGroup.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<FeatureGroupPayload> getOne(Long id) {
        return featureGroupTranslateRepository.getOneWithAllLang(id);
    }


    @Override
    public Page<FeatureGroupPayload> getAll(int page, int size, LanguageEnum languageEnum) {
        return featureGroupTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
    }

    @Override
    public ResponseEntity<?> deleteFeatureGroup(Long id){
        try {
            featureGroupRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error Feature Group",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FeatureGroupPayload> editFeatureGroup(List<FeatureGroupPayload> payloads){
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                FeatureGroup featureGroup = featureGroupRepository.findById(payloads.get(0).getId()).orElseThrow(()->new ResourceNotFoundException("feature not found"));
                featureGroupRepository.save(featureGroup);
                return payloads.stream().peek(featureGroupTranslate -> {
                    FeatureGroupTranslate featureGroupTranslate1 = featureGroupTranslateRepository.findById(featureGroupTranslate.getId()).orElseThrow(()->new ResourceNotFoundException("featuregrouptranslate not found"));
                    featureGroupTranslate1.setFeature(featureGroup);
                    featureGroupTranslate1.setLang(featureGroupTranslate.getLang());
                    featureGroupTranslate1.setName(featureGroupTranslate.getName());
                    featureGroupTranslateRepository.save(featureGroupTranslate1);
                    featureGroupTranslate1.setId(featureGroup.getId());
                }).collect(Collectors.toList());
            }
        }catch (Exception e){
            log.error("error Feature Group",e.getMessage());
        }
        return null;
    }
}
