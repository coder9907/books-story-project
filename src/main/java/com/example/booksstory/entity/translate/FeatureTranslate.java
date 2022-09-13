package com.example.booksstory.entity.translate;

import com.example.booksstory.entity.Feature;
import com.example.booksstory.entity.FeatureGroup;
import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//FeatureGropup ga tegiwli

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FeatureTranslate extends AbstractEntity {

    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Feature feature;

    @Enumerated(EnumType.STRING)
    private LanguageEnum lang;

}
