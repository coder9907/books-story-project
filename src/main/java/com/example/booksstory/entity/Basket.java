package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Basket extends AbstractEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> books;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;


}
