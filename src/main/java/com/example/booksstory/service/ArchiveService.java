package com.example.booksstory.service;

import com.example.booksstory.payload.ArchivePayload;
import com.example.booksstory.payload.UserPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ArchiveService {

    List<ArchivePayload> saveArchive(@RequestBody List<ArchivePayload> payloads);

    ResponseEntity<?> getByBookId(Long id);

    ResponseEntity<?> getByIdArchive(Long id);
}
