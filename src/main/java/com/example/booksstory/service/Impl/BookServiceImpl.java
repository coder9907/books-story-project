package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Autor;
import com.example.booksstory.entity.Book;
import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.entity.translate.BookTranslate;
import com.example.booksstory.entity.translate.CategoryTranslate;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.BookPayload;
import com.example.booksstory.payload.CategoryPayload;
import com.example.booksstory.repository.*;
import com.example.booksstory.repository.translate.BookTranslateRepository;
import com.example.booksstory.repository.translate.CategoryTranslateRepository;
import com.example.booksstory.service.BookService;
import com.example.booksstory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookTranslateRepository bookTranslateRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final AutorRepository autorRepository;
    private final BookRepository bookRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public List<BookPayload> save(List<BookPayload> payloads,Long subCategoryId) {
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                Book book = new Book();
                book.setPrice(payloads.get(0).getPrice());
                book.setAutor(autorRepository.findById(payloads.get(0).getAutorId()).orElseThrow(()-> new ResourceNotFoundException("Autor not found")));
                book.setAttachment(attachmentRepository.findByHashId(payloads.get(0).getHashId()));
                book.setSubCategory(Arrays.asList(subCategoryRepository.findById(subCategoryId).orElseThrow(()->new ResourceNotFoundException("subcategory not found"))));
                bookRepository.save(book);
                return payloads.stream().peek(bookPayload -> {
                    BookTranslate bookTranslate = new BookTranslate(bookPayload.getName(), bookPayload.getTitle(),bookPayload.getDiscription(),bookPayload.isFamous(),book,bookPayload.getLang());
                    bookTranslateRepository.save(bookTranslate);
                    bookPayload.setId(book.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<BookPayload> getOne(Long id) {
        return bookTranslateRepository.getOneWithAllLang(id);
    }


    @Override
    public Page<BookPayload> getAll(int page, int size, LanguageEnum languageEnum) {
        return bookTranslateRepository.getAllWithLang(PageRequest.of(page,size), languageEnum);
    }

    @Override
    public ResponseEntity<?> getAllBooks(){
        try {
            List<Book> books=bookRepository.findAll();
            if (books != null){
                return ResponseEntity.ok(books);
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("getAllBooks error",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAllBooks",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findBySubCategoryId(int page, int size, LanguageEnum languageEnum, Long id){
        try {
            Page<BookPayload> books=bookRepository.getAllBooks(PageRequest.of(page,size), languageEnum,id);
            if (books != null){
                return ResponseEntity.ok(books);
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error findBySubCategoryId",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAllBooks",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<BookPayload> editBook(List<BookPayload> payloads,Long subCategoryId) {
        try {
            if (payloads.size() == LanguageEnum.values().length) {
                Book book = bookRepository.findById(payloads.get(0).getId()).orElseThrow(()->new ResourceNotFoundException("book not found"));
                book.setPrice(payloads.get(0).getPrice());
                book.setAutor(autorRepository.findById(payloads.get(0).getAutorId()).orElseThrow(()-> new ResourceNotFoundException("Autor not found")));
                book.setAttachment(attachmentRepository.findByHashId(payloads.get(0).getHashId()));
                Autor autor=autorRepository.findById(payloads.get(0).getAutorId()).orElseThrow(()->new ResourceNotFoundException("autor not found"));
                book.setSubCategory(Arrays.asList(subCategoryRepository.findById(subCategoryId).orElseThrow(()->new ResourceNotFoundException("subcategory not found"))));
                book.setAutor(autor);
                bookRepository.save(book);
                return payloads.stream().peek(bookPayload -> {
                    BookTranslate bookTranslate = bookTranslateRepository.findById(bookPayload.getBookTranslateId()).orElseThrow(()->new ResourceNotFoundException("booktranslate not found"));
                    bookTranslate.setBook(book);
                    bookTranslate.setDiscription(bookPayload.getDiscription());
                    bookTranslate.setFamous(bookPayload.isFamous());
                    bookTranslate.setLang(bookPayload.getLang());
                    bookTranslate.setName(bookPayload.getName());
                    bookTranslate.setTitle(bookTranslate.getTitle());
                    bookTranslateRepository.save(bookTranslate);
                    bookPayload.setId(book.getId());
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBook(Long id){
        try {
            Book book=bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("book not found"));

            bookRepository.delete(book);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error delete book",e.getMessage());
            return new ResponseEntity(new Result(false,"no delete",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
