package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.Mp3UseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMp3StateDTO {
    Integer mp3No;
    Mp3UseYnEnum mp3UseYnEnum;
}
