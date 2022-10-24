package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertBookInfoDTO {
    Integer madeNatureNo;
    String title;
    String author;
    Date bookRegDt;
}
