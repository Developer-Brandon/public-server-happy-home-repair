package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {
    Integer bookNo;
    Integer madeNatureNo;
    String bookTitle;
    String bookAuthor;
    BookUseYnEnum bookUseYnEnum;
    Date bookRegDt;
    Date regDt;
}