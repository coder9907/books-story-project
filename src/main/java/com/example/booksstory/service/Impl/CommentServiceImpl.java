package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Comment;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.CommentPayload;
import com.example.booksstory.repository.BookRepository;
import com.example.booksstory.repository.CommentRepository;
import com.example.booksstory.repository.UserRepository;
import com.example.booksstory.service.CommentService;
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
public class  CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public List<CommentPayload> save(List<CommentPayload> payloads){
        try {
            if (payloads != null && payloads.size()>=0){
                 return payloads.stream().peek(commentPayload -> {
                     Comment comment=new Comment();
                     comment.setTitle(commentPayload.getTitle());
                     comment.setUsers(Arrays.asList(userRepository.findById(commentPayload.getUserId()).orElseThrow(()-> new ResourceNotFoundException("user not found"))));
                     comment.setBooks(Arrays.asList(bookRepository.findById(commentPayload.getBookId()).orElseThrow(()->new ResourceNotFoundException("boob not found"))));
                     commentRepository.save(comment);
                 }).collect(Collectors.toList());
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error save comment",e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getByIdComment(Long commentId){
        try {
            Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment not found"));
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            log.error("error Comment Service",e.getMessage());
            return new ResponseEntity(new Result(false,"error Comment Service",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findByBookId(Long bookId){
        try {
            List<CommentPayload> commentPayloads=commentRepository.getCommentByBooksId(bookId);
            return ResponseEntity.ok(commentPayloads);
        }catch (Exception e){
            log.error("error Comment Service",e.getMessage());
            return new ResponseEntity(new Result(false,"error Comment Service",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findByUserId(Long userId){
        try {
            List<CommentPayload> commentPayloads=commentRepository.getCommentByUsersId(userId);
            return ResponseEntity.ok(commentPayloads);
        }catch (Exception e){
            log.error("error Comment Service",e.getMessage());
            return new ResponseEntity(new Result(false,"error Comment Service",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId){
        try {
            commentRepository.deleteById(commentId);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error Comment Service",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editComment(CommentPayload commentPayload){
        try {
            Comment comment=commentRepository.findById(commentPayload.getId()).orElseThrow(()->new ResourceNotFoundException("comment not found"));
            comment.setTitle(commentPayload.getTitle());
            commentRepository.save(comment);
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            log.error("error Comment Service",e.getMessage());
            return new ResponseEntity(new Result(false,"edit no succesfull",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
