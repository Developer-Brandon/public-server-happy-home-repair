package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.ComicBookUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicBookVO {
    Integer comicBookNo;
    Integer madeNatureNo;
    String comicBookTitle;
    String comicBookAuthor;
    ComicBookUseYnEnum comicBookUseYnEnum;
    Date comicBookRegDt;
    Date regDt;
}
