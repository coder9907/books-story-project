package com.example.booksstory.repository.translate;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import com.example.booksstory.payload.FeatureGroupPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureGroupTranslateRepository extends JpaRepository<FeatureGroupTranslate, Long> {

    @Query("select new com.example.booksstory.payload.FeatureGroupPayload(f.id) from FeatureGroup f  where f.id=?1")
    public List<FeatureGroupPayload> getOneWithAllLang(Long id);


    @Query("select new com.example.booksstory.payload.FeatureGroupPayload(f.id, ff.name, ff.lang) from FeatureGroup f inner join f.featureTranslates ff where ff.lang=?1")
    public Page<FeatureGroupPayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);

}
