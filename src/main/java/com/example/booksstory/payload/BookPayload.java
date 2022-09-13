package com.example.booksstory.payload;

import com.example.booksstory.entity.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookPayload {

    private Long id;

    private String name;

    private String title;

    private String discription;

    private boolean famous;

    private String hashId;

    private Long price;

    private Long autorId;

    private Long subCategoryId;

    private Long bookTranslateId;

    private LanguageEnum lang;

    public BookPayload(Long id, String name, String title, String discription, boolean famous, String hashId, Long price, Long autorId, LanguageEnum lang) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.discription = discription;
        this.famous = famous;
        this.hashId = hashId;
        this.price = price;
        this.autorId = autorId;
        this.lang = lang;
    }

    public BookPayload(Long id, String name, String title, String discription, boolean famous, String hashId, Long price, Long autorId, Long subCategoryId, LanguageEnum lang) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.discription = discription;
        this.famous = famous;
        this.hashId = hashId;
        this.price = price;
        this.autorId = autorId;
        this.subCategoryId = subCategoryId;
        this.lang = lang;
    }
}
