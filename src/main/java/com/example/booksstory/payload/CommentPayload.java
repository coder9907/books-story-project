package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentPayload {

    private Long id;
    private String title;
    private Long userId;
    private Long bookId;

    public CommentPayload(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
