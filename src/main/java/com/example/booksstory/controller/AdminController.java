package com.example.booksstory.controller;

import com.example.booksstory.entity.Category;
import com.example.booksstory.entity.User;
import com.example.booksstory.entity.enums.LanguageEnum;
import com.example.booksstory.payload.*;
import com.example.booksstory.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final BookService bookService;
    private final AutorService autorService;
    private final DiscountService discountService;
    private final CommentService commentService;
    private final BasketService basketService;
    private final HistoryService historyService;
    private final ArchiveService archiveService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final FeatureGroupService featureGroupService;
    private final FeatureService featureService;

    @GetMapping("/user/all")
    public List<User> getallUser() {
        return userService.getAllUser();
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody List<UserPayload> payloads) {
        return userService.delete(payloads);
    }

    @PutMapping("/editUser")
    public List<UserPayload> editUser(@RequestBody List<UserPayload> payloads) {
        return userService.editUser(payloads);
    }

    @PostMapping("/archive")
    public List<ArchivePayload> saveArchive(@RequestBody List<ArchivePayload> payloads) {
        return archiveService.saveArchive(payloads);
    }

    @GetMapping("/archive/{bookId}")
    public ResponseEntity<?> getBookId(@PathVariable("bookId") Long id) {
        return archiveService.getByBookId(id);
    }

    @GetMapping("/archive/all/{archiveId}")
    public ResponseEntity<?> getByIdArchive(@PathVariable("archiveId") Long id) {
        return archiveService.getByIdArchive(id);
    }

    @PostMapping("/history")
    public List<HistoryPayload> saveHistory(@RequestBody List<HistoryPayload> payloads) {
        return historyService.saveHistory(payloads);
    }

    @GetMapping("/history/{historyId}")
    public ResponseEntity<?> getByIdHistory(@PathVariable("historyId") Long id) {
        return historyService.getByIdHistory(id);
    }

    @GetMapping("/history/all")
    public ResponseEntity<?> getAllHistory() {
        return historyService.getAllHistory();
    }

    @GetMapping("/history/book/{bookId}")
    public ResponseEntity<?> getByHistoryBookId(@PathVariable("bookId") Long id) {
        return historyService.getByHistoryBookId(id);
    }

    @GetMapping("/history/user/{userId}")
    public ResponseEntity<?> getByHistoryUserId(@PathVariable("userId") Long id) {
        return historyService.getByHistoryUserId(id);
    }

    @PostMapping("/basket")
    public List<BasketPayload> saveBasket(@RequestBody List<BasketPayload> payloads) {
        return basketService.saveBasket(payloads);
    }

    @GetMapping("/basket/basketBookId/{bookId}")
    public ResponseEntity<?> getAllByBasketBookId(@PathVariable("bookId") Long id) {
        return basketService.getAllBasketBookId(id);
    }

    @GetMapping("/basket/basketBookId/{userId}")
    public ResponseEntity<?> getAllByBasketUserId(@PathVariable("userId") Long id) {
        return basketService.getAllBasketUserId(id);
    }

    @GetMapping("/basket/all")
    public ResponseEntity<?> getAllBasket() {
        return basketService.getAll();
    }


    @DeleteMapping("/basket")
    public ResponseEntity<?> deleteBasket(@RequestBody List<BasketPayload> payloads) {
        return basketService.deleteBasket(payloads);
    }

    @PostMapping("/comment")
    public List<CommentPayload> saveComment(@RequestBody List<CommentPayload> commentPayloads) {
        return commentService.save(commentPayloads);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getByIdComment(@PathVariable("commentId") Long commentId) {
        return commentService.getByIdComment(commentId);
    }

    @GetMapping("/comment/book/{bookId}")
    public ResponseEntity<?> findByBookId(@PathVariable("bookId") Long bookId) {
        return commentService.findByBookId(bookId);
    }

    @GetMapping("/comment/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId") Long userId) {
        return commentService.findByUserId(userId);
    }

    @GetMapping("/comment/all")
    public ResponseEntity<?> getAllComment() {
        return commentService.getAllComment();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/comment")
    public ResponseEntity<?> editComment(@RequestBody CommentPayload commentPayload) {
        return commentService.editComment(commentPayload);
    }

    @PostMapping("/discount")
    public List<DiscountPayload> saveDiscount(@RequestBody List<DiscountPayload> payloads) {
        return discountService.save(payloads);
    }

    @PutMapping("/discount/{bookId}")
    public ResponseEntity<?> editDiscount(@RequestBody DiscountPayload payload, @PathVariable("bookId") Long bookId) {
        return discountService.editDiscount(payload, bookId);
    }

    @GetMapping("/discount/all")
    public ResponseEntity<?> getAllDiscount() {
        return discountService.getAllDiscount();
    }

    @DeleteMapping("/discount")
    public ResponseEntity<?> deleteDiscount(@RequestBody List<DiscountPayload> payloads) {
        return discountService.deleteDiscount(payloads);
    }

    @GetMapping("/discount/{discountId}")
    public ResponseEntity<?> getByIdDiscount(@PathVariable("discountId") Long id) {
        return discountService.getByIdDiscount(id);
    }

    @PostMapping("/autor")
    public List<AutorPayload> saveAutor(@RequestBody List<AutorPayload> payloads) {
        return autorService.save(payloads);
    }

    @PutMapping("/autor")
    public ResponseEntity<?> editAutor(@RequestBody AutorPayload autorPayload) {
        return autorService.editAutor(autorPayload);
    }

    @PutMapping("/autor/{autorId}")
    public ResponseEntity<?> deleteAutor(@PathVariable("autorId") Long id) {
        return autorService.deleteAutor(id);
    }

    @GetMapping("/autor/all")
    public ResponseEntity<?> getAllAutor() {
        return autorService.getAllAutor();
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<?> findByIdAutor(@PathVariable("autorId") Long id) {
        return autorService.findByIdAutor(id);
    }

    @GetMapping("/category")
    public Page<CategoryPayload> getAllCategory(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum) {
        return categoryService.getAll(page, size, languageEnum);
    }

    @PostMapping("/category")
    public List<CategoryPayload> saveCategory(@RequestBody List<CategoryPayload> payloads) {
        return categoryService.save(payloads);
    }

    @PutMapping("/category/edit")
    public List<CategoryPayload> editCategory(@RequestBody List<CategoryPayload> payloads) {
        return categoryService.editCategory(payloads);
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody List<CategoryPayload> payloads) {
        return categoryService.deleteByIdCategory(payloads);
    }

    @GetMapping("/category/{id}")
    public List<CategoryPayload> getOneWithCategoryId(@PathVariable Long id) {
        return categoryService.getOne(id);
    }

    @GetMapping("/subcategory")
    public Page<SubCategoryPayload> getAllSubCategory(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum) {
        return subCategoryService.getAll(page, size, languageEnum);
    }

    @PostMapping("/subcategory")
    public List<SubCategoryPayload> saveSubCategory(@RequestBody List<SubCategoryPayload> payloads) {
        return subCategoryService.save(payloads);
    }

    @GetMapping("/subcategory/{subcategoryId}")
    public List<SubCategoryPayload> getOneWithId(@PathVariable("subcategoryId") Long id) {
        return subCategoryService.getOne(id);
    }

    @GetMapping("/subcategory/all/{categoryId}")
    public List<SubCategoryPayload> getAllBySubCategory(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum, @PathVariable("categoryId") Long categoryId) {
        return subCategoryService.getBySubCategoryCategoryId(page, size, languageEnum, categoryId);
    }

    @PutMapping("/subcategory/edit")
    public List<SubCategoryPayload> editSubCategory(@RequestBody List<SubCategoryPayload> payloads) {
        return subCategoryService.editSubCategory(payloads);
    }

    @DeleteMapping("/subcategory/delete/{subcategoryId}")
    public ResponseEntity<?> deleteSubcategory(@PathVariable("subcategoryId") Long id) {
        return subCategoryService.deleteSubcategory(id);
    }

    @GetMapping("/book")
    public Page<BookPayload> getAllBook(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum) {
        return bookService.getAll(page, size, languageEnum);
    }

    @PostMapping("/book/{subCategoryId}")
    public List<BookPayload> saveBook(@RequestBody List<BookPayload> payloads, @PathVariable("subCategoryId") Long subCategoryId) {
        return bookService.save(payloads, subCategoryId);
    }

    @GetMapping("/book/{id}")
    public List<BookPayload> getOneWithIdBook(@PathVariable Long id) {
        return bookService.getOne(id);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/book/edit/{subcategoryId}")
    public List<BookPayload> editBook(@RequestBody List<BookPayload> payloads, @PathVariable("subcategoryId") Long id) {
        return bookService.editBook(payloads, id);
    }

    @PostMapping("/favorite")
    public ResponseEntity<?> saveFavorite(@RequestBody List<FavoritePayload> payloads) {
        return favoriteService.saveFavorite(payloads);
    }

    @GetMapping("/favourite")
    public ResponseEntity<?> getAllFavourite() {
        return favoriteService.getAllFavourite();
    }

    @GetMapping("/favourite/user/{userId}")
    public ResponseEntity<?> getByFavouriteUserId(@PathVariable("userId") Long id) {
        return favoriteService.getByFavoriteUserId(id);
    }

    @GetMapping("/favourite/book/{bookId}")
    public ResponseEntity<?> getByFavouriteBookId(@PathVariable("bookId") Long id) {
        return favoriteService.getByFavoriteBookId(id);
    }


    @DeleteMapping("/favorite/{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable("favoriteId") Long id) {
        return favoriteService.deleteFavorite(id);
    }

    @PostMapping("/featureGroup")
    public List<FeatureGroupPayload> saveFeatureGroup(@RequestBody List<FeatureGroupPayload> payloads) {
        return featureGroupService.save(payloads);
    }

    @GetMapping("/featureGroup/{featureGroupId}")
    public List<FeatureGroupPayload> getOneFeatureGroup(@PathVariable("featureGroupId") Long id) {
        return featureGroupService.getOne(id);
    }

    @GetMapping("/featureGroup/all")
    public Page<FeatureGroupPayload> getAllFeatureGroup(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum) {
        return featureGroupService.getAll(page, size, languageEnum);
    }

    @DeleteMapping("/featureGroup/{featureGroupId}")
    public ResponseEntity<?> deleteFeatureGroup(@PathVariable("featureGroupId") Long id) {
        return featureGroupService.deleteFeatureGroup(id);
    }

    @PutMapping("/featureGroup")
    public List<FeatureGroupPayload> editFeatureGroup(@RequestBody List<FeatureGroupPayload> payloads) {
        return featureGroupService.editFeatureGroup(payloads);
    }

    @PostMapping("/feature/{bookId}")
    public List<FeaturePayload> saveFeature(@RequestBody List<FeaturePayload> payloads, @PathVariable("bookId") Long id) {
        return featureService.save(payloads, id);
    }

    @GetMapping("/feature/{featureId}")
    public List<FeaturePayload> getOneFeature(@PathVariable("featureId") Long id) {
        return featureService.getOne(id);
    }

    @GetMapping("/feature/all")
    public Page<FeaturePayload> getAllFeature(@RequestParam(defaultValue = "0") int page, int size, @RequestParam(name = "lang", defaultValue = "uz") LanguageEnum languageEnum) {
        return featureService.getAll(page, size, languageEnum);
    }


    @DeleteMapping("/feature/{featureId}")
    public ResponseEntity<?> deleteFeature(@PathVariable("featureId") Long id) {
        return featureService.deleteFeature(id);
    }

    @PutMapping("/feture")
    public List<FeaturePayload> editFeature(@RequestBody List<FeaturePayload> payloads) {
        return featureService.editFeature(payloads);
    }

}
