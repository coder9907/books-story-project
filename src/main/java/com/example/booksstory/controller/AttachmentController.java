package com.example.booksstory.controller;

import com.example.booksstory.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @GetMapping("/")
    public String get() {
        return "Hello:)";
    }

    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println(multipartFile.toString());
        return attachmentService.upload(multipartFile);
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity<?> getPreview(@PathVariable("hashId") String hashId) {
        return attachmentService.preview(hashId);
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> getDownload(@PathVariable("hashId") String hashId) {
        return attachmentService.download(hashId);
    }

    @GetMapping("/delete/{hashId}")
    public ResponseEntity<?> getDelete(@PathVariable("hashId") String hashId) {
        return attachmentService.delete(hashId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttachment() {
        return attachmentService.getAllAttachment();
    }

}
