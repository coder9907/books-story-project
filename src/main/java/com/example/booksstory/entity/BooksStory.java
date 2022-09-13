package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BooksStory extends AbstractEntity {

    @Column(length = 50)
    private String name;

    private String address;

}
