package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.FeatureGroupTranslate;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

//FeatureGropup ga tegiwli

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FeatureGroup extends AbstractEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "feature")
    private List<FeatureGroupTranslate> featureTranslates;


}
