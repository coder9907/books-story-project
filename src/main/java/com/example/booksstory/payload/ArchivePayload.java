package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArchivePayload {

    private Long id;
    private Long userId;
    private Long bookId;

}
