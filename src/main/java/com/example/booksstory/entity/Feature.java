package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//FeatureGropup ga tegiwli

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Feature extends AbstractEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "feature")
    private List<FeatureGroupTranslate> featureTranslates;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private FeatureGroup featureGroup;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Book> books;

}
