package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Basket;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.BasketPayload;
import com.example.booksstory.repository.BasketRepository;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<BasketPayload> saveBasket(List<BasketPayload> payloads){
        try {
            if (payloads != null && payloads.size()>=0){
                return payloads.stream().peek(basketPayload -> {
                    Basket basket=new Basket();
                    basket.setBooks(Arrays.asList(bookRepository.findById(basketPayload.getBookId()).orElseThrow(()->new ResourceNotFoundException("book not found"))));
                    basket.setUsers(Arrays.asList(userRepository.findById(basketPayload.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found"))));
                    basketRepository.save(basket);
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("basket save error", e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBasket(List<BasketPayload> payloads){
        try {
            if (payloads != null){
                payloads.stream().peek(basketPayload -> {
                    basketRepository.deleteById(basketPayload.getBasketId());
                }).collect(Collectors.toList());
                return ResponseEntity.ok("delete succesfull");
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("delete no succesfull",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
