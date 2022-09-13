package com.example.booksstory.entity.translate;

import com.example.booksstory.entity.BooksStory;
import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.abstractentity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode
public class CategoryTranslate extends AbstractEntity {

    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Category category;

    @Enumerated(EnumType.STRING)
    private LanguageEnum lang;

}
