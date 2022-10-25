package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertMovieInfoDTO {
    Integer insertedMovieNo;
    Integer madeNatureNo;
    String title;
    String directorName;
    Integer pagePerMovieCnt;
    Date movieRegDt;
}
