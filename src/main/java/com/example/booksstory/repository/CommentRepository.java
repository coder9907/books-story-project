package com.example.booksstory.repository;

import com.example.booksstory.entity.Comment;
import com.example.booksstory.payload.CommentPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.example.booksstory.payload.CommentPayload(c.id,c.title) from Comment c inner join c.books cb where cb.id=?1")
    List<CommentPayload> getCommentByBooksId(Long id);

    @Query("select new com.example.booksstory.payload.CommentPayload(c.id,c.title) from Comment c inner join c.users cu where cu.id=?1")
    List<CommentPayload> getCommentByUsersId(Long id);
}
