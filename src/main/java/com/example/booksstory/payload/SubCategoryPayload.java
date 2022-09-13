package com.example.booksstory.payload;

import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategoryPayload {

    private Long id;
    private String name;
    private Long categoryId;
    private Long subCategoryTranslateId;
    private LanguageEnum lang;

    public SubCategoryPayload(String name, Long categoryId, LanguageEnum lang) {
        this.name = name;
        this.categoryId = categoryId;
        this.lang = lang;
    }

    public SubCategoryPayload(String name, LanguageEnum lang) {
        this.name = name;
        this.lang = lang;
    }

    public SubCategoryPayload(Long id, String name, Long categoryId, LanguageEnum lang) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.lang = lang;
    }
}
