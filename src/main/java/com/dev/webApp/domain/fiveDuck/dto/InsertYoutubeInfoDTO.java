package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class InsertYoutubeInfoDTO {
    String title;
    String youtuberName;
    Integer likeCnt;
    Integer dislikeCnt;
    Integer viewCnt;
    Date youtubeRegDt;
}
