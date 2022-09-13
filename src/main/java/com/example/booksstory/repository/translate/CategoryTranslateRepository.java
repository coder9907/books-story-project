package com.example.booksstory.repository.translate;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.CategoryTranslate;
import com.example.booksstory.payload.CategoryPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryTranslateRepository extends JpaRepository<CategoryTranslate, Long> {

    @Query("select new com.example.booksstory.payload.CategoryPayload(c.id, t.name, t.id, t.lang) from Category c inner join c.categoryTranslates t where c.id=?1")
    public List<CategoryPayload> getOneWithAllLang(Long id);


    @Query("select new com.example.booksstory.payload.CategoryPayload(b.id, bt.name, bt.id, bt.lang) from Category b inner join b.categoryTranslates bt where bt.lang=?1")
    public Page<CategoryPayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);

}
