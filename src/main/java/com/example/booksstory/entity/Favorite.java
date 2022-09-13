package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Favorite extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<User> user;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Book> book;
}
