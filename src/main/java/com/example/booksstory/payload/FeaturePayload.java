package com.example.booksstory.payload;

import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeaturePayload {

    private Long id;
    private String name;
    private Long featureGroupId;
    private LanguageEnum lang;

    public FeaturePayload(Long id, String name, LanguageEnum lang) {
        this.id = id;
        this.name = name;
        this.lang = lang;
    }
}
