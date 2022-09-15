package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoritePayload {

    private Long id;
    private Long userId;
    private Long bookId;

    public FavoritePayload(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }


}
