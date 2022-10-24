package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateBookStateDTO {
    Integer bookNo;
    BookUseYnEnum bookUseYnEnum;
}
