package com.example.booksstory.repository.translate;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.CategoryTranslate;
import com.example.booksstory.entity.translate.SubCategoryTranslate;
import com.example.booksstory.payload.CategoryPayload;
import com.example.booksstory.payload.SubCategoryPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryTranslateRepository extends JpaRepository<SubCategoryTranslate, Long> {

    @Query("select new com.example.booksstory.payload.SubCategoryPayload(c.id, cs.name, c.category.id, cs.lang) from SubCategory c inner join c.subCategoryTranslates cs where c.id=?1")
    public List<SubCategoryPayload> getOneWithAllLang(Long id);


    @Query("select new com.example.booksstory.payload.SubCategoryPayload(c.id, cs.name, c.category.id, cs.lang) from SubCategory c inner join c.subCategoryTranslates cs where cs.lang=?1")
    public Page<SubCategoryPayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);

    @Query("select new com.example.booksstory.payload.SubCategoryPayload(s.id, ss.name, s.category.id, ss.lang) from SubCategory s inner join s.subCategoryTranslates ss where ss.lang=?1 and s.category.id=?2")
    public List<SubCategoryPayload> getAllSubCategoryCategoryId(Pageable pageable, LanguageEnum languageEnum, Long id);

}
