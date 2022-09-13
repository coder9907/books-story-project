package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Discount;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.DiscountPayload;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.DiscountRepository;
import com.example.booksstory.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final BookRepository bookRepository;

    @Override
    public List<DiscountPayload> save(List<DiscountPayload> payloads){
        try {
            if (payloads != null && payloads.size()>=0){
                return payloads.stream().peek(discountPayload -> {
                    Discount discount=new Discount();
                    discount.setDiscount(discountPayload.getDiscount());
                    discount.setBook(bookRepository.findById(discountPayload.getBookId()).orElseThrow(()->new ResourceNotFoundException("book not found")));
                    discountRepository.save(discount);
                }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error discount",e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> editDiscount(DiscountPayload discountPayload, Long bookId){
        try {
            Discount discount=discountRepository.findById(discountPayload.getId()).orElseThrow(()->new ResourceNotFoundException("discount not found"));
            discount.setDiscount(discountPayload.getDiscount());
            discount.setBook(bookRepository.getById(bookId));
            discountRepository.save(discount);
            return ResponseEntity.ok(discount);
        }catch (Exception e){
            log.error("error edit discount",e.getMessage());
            return new ResponseEntity(new Result(false,"error edit discount",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllDiscount(){
        try {
            List<Discount> discounts=discountRepository.findAll();
            return ResponseEntity.ok(discounts);
        }catch (Exception e){
            log.error("error getAll discount",e.getMessage());
            return new ResponseEntity(new Result(false,"error getAll discount",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteDiscount(List<DiscountPayload> payloads){
        try {
            payloads.stream().peek(discountPayload -> {
                discountRepository.deleteById(discountPayload.getId());
            }).collect(Collectors.toList());
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error delete discount",e.getMessage());
            return new ResponseEntity(new Result(false,"error delete discount",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getByIdDiscount(Long id){
        try {
            Discount discount=discountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("discount not found"));
            return ResponseEntity.ok(discount);
        }catch (Exception e){
            log.error("error getById discount",e.getMessage());
            return new ResponseEntity(new Result(false,"error getById discount",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
