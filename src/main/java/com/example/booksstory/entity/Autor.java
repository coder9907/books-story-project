package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Autor extends AbstractEntity {

    @Column(length = 50, nullable = false)
    private String name;

}
