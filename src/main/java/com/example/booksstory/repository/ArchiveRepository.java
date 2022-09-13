package com.example.booksstory.repository;

import com.example.booksstory.entity.Archive;
import com.example.booksstory.entity.Discount;
import com.example.booksstory.payload.ArchivePayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    @Query("select new com.example.booksstory.payload.ArchivePayload(a.id,b.id,u.id) from Archive a inner join Book b inner join users u where b.id=?1")
    List<ArchivePayload> getAllByArchive(Long id);

}
