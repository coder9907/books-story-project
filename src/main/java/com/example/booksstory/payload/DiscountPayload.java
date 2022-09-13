package com.example.booksstory.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountPayload {

    private Long id;

    private Long discount;

    private Long bookId;

}
