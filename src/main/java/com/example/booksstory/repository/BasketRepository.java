package com.example.booksstory.repository;

import com.example.booksstory.entity.Admin;
import com.example.booksstory.entity.Basket;
import com.example.booksstory.payload.BasketPayload;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query("select new com.example.booksstory.payload.BasketPayload(b.id, bb.id) from Basket b inner join b.books bb where bb.id=?1")
    List<BasketPayload> getAllByBasketBookId(Long id);

    @Query("select new com.example.booksstory.payload.BasketPayload(b.id, bu.id) from Basket b inner join b.users bu where bu.id=?1")
    List<BasketPayload> getAllByBasketUserId(Long id);

}
