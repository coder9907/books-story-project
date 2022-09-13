package com.example.booksstory.repository;

import com.example.booksstory.entity.History;
import com.example.booksstory.payload.HistoryPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select new com.example.booksstory.payload.HistoryPayload(h.id,b.id) from History h inner join Book b where b.id=?1")
    List<HistoryPayload> getAllByHistory(Long id);



}
