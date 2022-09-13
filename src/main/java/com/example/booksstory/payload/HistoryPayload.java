package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryPayload {

    private Long id;
    private Long bookId;
    private Long userID;

    public HistoryPayload(Long bookId) {
        this.bookId = bookId;
    }

    public HistoryPayload(Long id, Long bookId) {
        this.id = id;
        this.bookId = bookId;
    }
}
