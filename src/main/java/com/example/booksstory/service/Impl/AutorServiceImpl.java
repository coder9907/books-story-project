package com.example.booksstory.service.Impl;

import com.example.booksstory.entity.Autor;
import com.example.booksstory.exception.ResourceBadRequestException;
import com.example.booksstory.exception.ResourceNotFoundException;
import com.example.booksstory.model.Result;
import com.example.booksstory.payload.AutorPayload;
import com.example.booksstory.repository.AutorRepository;
import com.example.booksstory.service.AutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    @Override
    public List<AutorPayload> save(List<AutorPayload> payloads){

        try {
            if (payloads != null){
                return payloads.stream().peek(autorPayload -> {
                    Autor autor=new Autor();
                    autor.setName(autorPayload.getName());
                    autorRepository.save(autor);
                }).collect(Collectors.toList());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> editAutor(AutorPayload autorPayload){
        try {
            Autor autor=autorRepository.findById(autorPayload.getId()).orElseThrow(()->new ResourceNotFoundException("autor not found"));
            if (autor != null){
                autor.setName(autorPayload.getName());
                autorRepository.save(autor);
                return ResponseEntity.ok(autor);
            }throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("error edit Autor",e.getMessage());
            return new ResponseEntity(new Result(false,"edit no succesful",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteAutor(Long id){
        try {
            autorRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error delete Autor",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesful",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllAutor(){
        try {
            List<Autor> autors=autorRepository.findAll();
            return ResponseEntity.ok(autors);
        }catch (Exception e){
            log.error("error delete Autor",e.getMessage());
            return new ResponseEntity(new Result(false,"delete no succesful",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findByIdAutor(Long id){
        try {
            Autor autor=autorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("autor not found"));
            return ResponseEntity.ok(autor);
        }catch (Exception e){
            log.error("error findByIdAutor",e.getMessage());
            return new ResponseEntity(new Result(false,"delete findByIdAutor",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
