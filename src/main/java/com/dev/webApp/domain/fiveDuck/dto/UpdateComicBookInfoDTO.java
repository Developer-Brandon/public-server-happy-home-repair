package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateComicBookInfoDTO {
    Integer bookNo;
    Integer madeNatureNo;
    String title;
    String author;
    Date comicBookRegDt;
}