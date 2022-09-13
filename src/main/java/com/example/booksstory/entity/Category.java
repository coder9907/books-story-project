package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.CategoryTranslate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbstractEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,orphanRemoval = true, mappedBy = "category")
    private List<CategoryTranslate> categoryTranslates;



}
