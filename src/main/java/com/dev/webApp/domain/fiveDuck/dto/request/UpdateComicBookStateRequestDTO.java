package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateComicBookStateRequestDTO {
    Integer bookNo;
    ComicBookUseYnEnum comicBookUseYnEnum;
}
