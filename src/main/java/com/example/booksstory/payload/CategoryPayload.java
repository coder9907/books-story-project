package com.example.booksstory.payload;

import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryPayload {

    private Long id;
    private String name;
    private Long CategoryTranslateId;
    private LanguageEnum lang;

    public CategoryPayload(String name, LanguageEnum lang) {
        this.name = name;
        this.lang = lang;
    }

    public CategoryPayload(Long id, String name, LanguageEnum lang) {
        this.id = id;
        this.name = name;
        this.lang = lang;
    }
}
