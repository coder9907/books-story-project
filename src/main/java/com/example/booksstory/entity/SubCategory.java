package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.CategoryTranslate;
import com.example.booksstory.entity.translate.SubCategoryTranslate;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubCategory extends AbstractEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "subCategory")
    private List<SubCategoryTranslate> subCategoryTranslates;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Category category;

}
