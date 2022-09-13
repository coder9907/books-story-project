package com.example.booksstory.repository;

import com.example.booksstory.entity.Admin;
import com.example.booksstory.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
