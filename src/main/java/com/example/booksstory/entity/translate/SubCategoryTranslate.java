package com.example.booksstory.entity.translate;

import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.SubCategory;
import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubCategoryTranslate extends AbstractEntity {

    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private SubCategory subCategory;

    @Enumerated(EnumType.STRING)
    private LanguageEnum lang;

}
