package com.example.booksstory.entity.translate;

import com.example.booksstory.entity.Book;
import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BookTranslate extends AbstractEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition="TEXT")
    private String discription;

    private boolean famous;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Book book;

    @Enumerated(EnumType.STRING)
    private LanguageEnum lang;

}
