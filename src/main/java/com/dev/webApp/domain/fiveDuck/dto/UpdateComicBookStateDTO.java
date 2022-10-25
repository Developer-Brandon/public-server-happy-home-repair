package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateComicBookStateDTO {
    Integer bookNo;
    ComicBookUseYnEnum comicBookUseYnEnum;
}
