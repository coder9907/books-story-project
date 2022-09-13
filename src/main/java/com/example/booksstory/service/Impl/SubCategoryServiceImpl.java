package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.SubCategory;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.SubCategoryTranslate;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.SubCategoryPayload;
import com.example.booksstory.repository.CategoryRepository;
import com.example.booksstory.repository.SubCategoryRepository;
import com.example.booksstory.repository.translate.SubCategoryTranslateRepository;
import com.example.booksstory.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryTranslateRepository subCategoryTranslateRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<SubCategoryPayload> save(List<SubCategoryPayload> subCategoryPayloads){
        try {
            if (subCategoryPayloads.size()== LanguageEnum.values().length){
                SubCategory subCategory=new SubCategory();
                subCategory.setCategory(categoryRepository.findById(subCategoryPayloads.get(0).getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("not found Category By Id")));
                subCategoryRepository.save(subCategory);

                return subCategoryPayloads.stream().peek(subCategoryPayload -> {
                    SubCategoryTranslate subCategoryTranslate=new SubCategoryTranslate(subCategoryPayload.getName(),subCategory,subCategoryPayload.getLang());
                    subCategoryTranslateRepository.save(subCategoryTranslate);
                    subCategoryPayload.setId(subCategory.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("save no succesfull");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<SubCategoryPayload> getOne(Long id){
        return subCategoryTranslateRepository.getOneWithAllLang(id);
    }

    @Override
    public Page<SubCategoryPayload> getAll(int page, int size, LanguageEnum languageEnum) {
        return subCategoryTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
    }

    @Override
    public List<SubCategoryPayload> getBySubCategoryCategoryId(int page, int size, LanguageEnum languageEnum, Long categoryId){
        try {
            return subCategoryTranslateRepository.getAllSubCategoryCategoryId(PageRequest.of(page,size),languageEnum,categoryId);
        }catch (Exception e){
            log.error("error getBySubCategoryCategoryId",e.getMessage());
        }
        return null;
    }

    @Override
    public List<SubCategoryPayload> editSubCategory(List<SubCategoryPayload> payloads){
        try {
            if (payloads != null){
                SubCategory subCategory=subCategoryRepository.findById(payloads.get(0).getId()).orElseThrow(()->new ResourceNotFoundException());
                subCategoryRepository.save(subCategory);
                return payloads.stream().peek(subCategoryPayload -> {
                    SubCategoryTranslate subCategoryTranslate=subCategoryTranslateRepository.findById(subCategoryPayload.getSubCategoryTranslateId()).orElseThrow(()->new ResourceNotFoundException("Subcategory not found"));
                    subCategoryTranslate.setName(subCategoryPayload.getName());
                    subCategoryTranslate.setLang(subCategoryPayload.getLang());
                    subCategoryTranslateRepository.save(subCategoryTranslate);
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error edit subcategory",e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSubcategory(Long id){
        try {
            SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("subcategory not found"));
            if (subCategory != null){
                subCategoryRepository.deleteById(id);
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("delete no succesfull",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete subcategory",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
