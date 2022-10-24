package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.Mp3UseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mp3VO {
    Integer mp3No;
    String mp3Title;
    String singer;
    Mp3UseYnEnum mp3UseYnEnum;
    Date songRegDt;
    Date regDt;
}
