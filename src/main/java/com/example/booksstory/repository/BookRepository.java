package com.example.booksstory.repository;

import com.example.booksstory.entity.Book;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.BookPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select new com.example.booksstory.payload.BookPayload(b.id, bb.name, bb.title, bb.discription, bb.famous, b.attachment.hashId, b.price, b.autor.id, sc.id, bb.lang) from Book b inner join b.bookTranslates bb inner join SubCategory sc where bb.lang=?1 and sc.id=?2")
    public Page<BookPayload> getAllBooks(Pageable pageable, LanguageEnum languageEnum, Long subCategory);

    Book getById(Long id);

}
