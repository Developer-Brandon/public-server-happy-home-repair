package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.BookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeleteBookInfoDTO {
    Integer bookNo;
}
