package com.example.booksstory.repository;

import com.example.booksstory.entity.Favorite;
import com.example.booksstory.payload.BasketPayload;
import com.example.booksstory.payload.FavoritePayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("select new com.example.booksstory.payload.FavoritePayload(f.id, fb.id) from Favorite f inner join f.book fb where fb.id=?1")
    List<FavoritePayload> getAllByBasketBookId(Long id);

    @Query("select new com.example.booksstory.payload.FavoritePayload(f.id, fu.id) from Favorite f inner join f.user fu where fu.id=?1")
    List<FavoritePayload> getAllByBasketUserId(Long id);

}
