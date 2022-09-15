package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Favorite;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.FavoritePayload;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.FavoriteRepository;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<?> saveFavorite(List<FavoritePayload> payloads){
        try {
            if (payloads != null){
                payloads.stream().peek(favoritePayload -> {
                    Favorite favorite=new Favorite();
                    favorite.setUser(Arrays.asList(userRepository.findById(favoritePayload.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"))));
                    favorite.setBook(Arrays.asList(bookRepository.findById(favoritePayload.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Book not found"))));
                    favoriteRepository.save(favorite);
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error save favorite");
            return new ResponseEntity(new Result(false,"no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteFavorite(Long id){
        try {
            favoriteRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error delete favorite");
            return new ResponseEntity(new Result(false,"no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getByFavoriteUserId(Long userId){
        try {
            List<FavoritePayload> favoritePayloads=favoriteRepository.getAllByBasketUserId(userId);
            return ResponseEntity.ok(favoritePayloads);
        }catch (Exception e){
            log.error("error favorite");
            return new ResponseEntity(new Result(false,"no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getByFavoriteBookId(Long bookId){
        try {
            List<FavoritePayload> favoritePayloads=favoriteRepository.getAllByBasketBookId(bookId);
            return ResponseEntity.ok(favoritePayloads);
        }catch (Exception e){
            log.error("error favorite");
            return new ResponseEntity(new Result(false,"no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllFavourite(){
        try {
            return ResponseEntity.ok(favoriteRepository.findAll());
        }catch (Exception e){
            log.error("error favorite");
            return new ResponseEntity(new Result(false,"no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
