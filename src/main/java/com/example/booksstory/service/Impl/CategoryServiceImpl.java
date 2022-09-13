package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.CategoryTranslate;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.CategoryPayload;
import com.example.booksstory.repository.CategoryRepository;
import com.example.booksstory.repository.translate.CategoryTranslateRepository;
import com.example.booksstory.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryTranslateRepository categoryTranslateRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryPayload> save(List<CategoryPayload> payloads) {
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                Category category = new Category();
                categoryRepository.save(category);
                return payloads.stream().peek(categoryPayload -> {
                    CategoryTranslate categoryTranslate = new CategoryTranslate(categoryPayload.getName(),category,categoryPayload.getLang());
                    categoryTranslateRepository.save(categoryTranslate);
                    categoryPayload.setId(category.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<CategoryPayload> getOne(Long id) {
        return categoryTranslateRepository.getOneWithAllLang(id);
    }


    @Override
    public Page<CategoryPayload> getAll(int page, int size, LanguageEnum languageEnum) {
        return categoryTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
    }

    @Override
    public ResponseEntity<?> deleteByIdCategory(List<CategoryPayload> payloads){
        try {
            if (payloads != null){
                payloads.stream().peek(categoryPayload -> {
                    categoryRepository.deleteById(categoryPayload.getId());
                }).collect(Collectors.toList());
                return ResponseEntity.ok("delete succesfull");
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error delete category",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete category",null ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CategoryPayload> editCategory(List<CategoryPayload> payloads){
        try {
            if (payloads.size() == LanguageEnum.values().length){
                Category category=categoryRepository.findById(payloads.get(0).getId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
                categoryRepository.save(category);
                return payloads.stream().peek(categoryPayload -> {
                    CategoryTranslate categoryTranslate = categoryTranslateRepository.findById(categoryPayload.getCategoryTranslateId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
                    CategoryTranslate categoryTranslate1= new CategoryTranslate(categoryPayload.getName(),category,categoryPayload.getLang());
                    categoryTranslate1.setCreatedAt(categoryTranslate.getCreatedAt());
                    categoryTranslate1.setId(categoryPayload.getCategoryTranslateId());
                    categoryTranslateRepository.save(categoryTranslate1);
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("reply properly body");
        }catch (Exception e){
            log.error("error edit category",e.getMessage());
        }
        return null;
    }

}
