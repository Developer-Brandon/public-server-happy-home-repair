package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertMp3InfoDTO {
    Integer insertedMp3No;
    String title;
    String singer;
    Date songRegDt;
}
