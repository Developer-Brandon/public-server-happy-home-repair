package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertMovieDTO {
    Integer madeNatureNo;
    String title;
    String director;
    Integer pagePerMovieCnt;
    Date movieRegDt;
}
