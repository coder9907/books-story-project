package com.example.booksstory.repository.translate;

import com.example.booksstory.entity.Feature;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import com.example.booksstory.entity.translate.FeatureTranslate;
import com.example.booksstory.payload.FeatureGroupPayload;
import com.example.booksstory.payload.FeaturePayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureTranslateRepository extends JpaRepository<FeatureTranslate, Long> {

    @Query("select new com.example.booksstory.payload.FeaturePayload(f.id, ff.name, ff.lang) from Feature f  inner join  f.featureTranslates ff where ff.id=?1")
    public List<FeaturePayload> getOneWithAllLang(Long id);


    @Query("select new com.example.booksstory.payload.FeaturePayload(f.id, ff.name, ff.lang) from Feature f inner join f.featureTranslates ff where ff.lang=?1")
    public Page<FeaturePayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);

}
