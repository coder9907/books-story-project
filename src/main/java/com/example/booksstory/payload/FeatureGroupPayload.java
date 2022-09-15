package com.example.booksstory.payload;

import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureGroupPayload {

    private Long id;
    private String name;
    private LanguageEnum lang;

    public FeatureGroupPayload(Long id) {
        this.id = id;
    }
}
