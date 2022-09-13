package com.example.booksstory.repository.translate;

import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.BookTranslate;
import com.example.booksstory.payload.BookPayload;
import com.example.booksstory.payload.CategoryPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTranslateRepository extends JpaRepository<BookTranslate,Long> {

    @Query("select new com.example.booksstory.payload.BookPayload(b.id, bb.name, bb.title, bb.discription, bb.famous, b.attachment.hashId, b.price, b.autor.id, bb.lang) from Book b inner join b.bookTranslates bb where b.id=?1")
    public List<BookPayload> getOneWithAllLang(Long id);

    @Query("select new com.example.booksstory.payload.BookPayload(b.id, bb.name, bb.title, bb.discription, bb.famous, b.attachment.hashId, b.price, b.autor.id, bb.lang) from Book b inner join b.bookTranslates bb where bb.lang=?1")
    public Page<BookPayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);



}
