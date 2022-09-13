package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.example.booksstory.entity.translate.BookTranslate;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Archive extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Book> books;

}
