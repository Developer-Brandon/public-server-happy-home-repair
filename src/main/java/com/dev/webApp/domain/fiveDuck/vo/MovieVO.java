package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.MovieUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieVO {
    Integer movieNo;
    Integer madeNatureNo;
    String movieTitle;
    String directorName;
    Integer pagePerMovieCnt;
    MovieUseYnEnum movieUseYnEnum;
    Date movieRegDt;
    Date regDt;
}