package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.BookTranslate;
import com.example.booksstory.entity.translate.SubCategoryTranslate;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Book extends AbstractEntity {

    private Long price;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "book")
    private List<BookTranslate> bookTranslates;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SubCategory> subCategory;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Autor autor;

}
