package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.BookTranslate;
import com.example.booksstory.entity.translate.SubCategoryTranslate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book extends AbstractEntity {

    private Long price;

    @ToString.Exclude
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Attachment attachment;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "book")
    private List<BookTranslate> bookTranslates;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<SubCategory> subCategory;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Autor autor;

}
