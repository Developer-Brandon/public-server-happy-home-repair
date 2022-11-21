package com.dev.webApp.domain.fiveDuck.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UpdateMp3InfoRequestDTO {
    Integer mp3No;
    String title;
    String singer;
    Date songRegDt;
}
