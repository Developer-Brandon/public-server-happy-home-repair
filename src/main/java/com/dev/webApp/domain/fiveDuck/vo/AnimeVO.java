package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.AnimeUseYnEnum;
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
public class AnimeVO {
    Integer animeNo;
    Integer madeNatureNo;
    String animeTitle;
    String animeAuthor;
    Integer pagePerAnimeCnt;
    AnimeUseYnEnum animeUseYnEnum;
    Date animeRegDt;
    Date regDt;
}
