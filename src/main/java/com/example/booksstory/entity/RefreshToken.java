package com.example.booksstory.entity;

import com.example.booksstory.entity.abstractentity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class RefreshToken extends AbstractEntity {

    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date expiredTime;

}


