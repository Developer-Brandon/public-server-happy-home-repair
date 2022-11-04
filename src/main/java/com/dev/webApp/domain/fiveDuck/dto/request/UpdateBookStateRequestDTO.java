package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateBookStateRequestDTO {
    Integer bookNo;
    BookUseYnEnum bookUseYnEnum;
}
