package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.Mp3UseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateMp3StateRequestDTO {
    Integer mp3No;
    Mp3UseYnEnum mp3UseYnEnum;
}
