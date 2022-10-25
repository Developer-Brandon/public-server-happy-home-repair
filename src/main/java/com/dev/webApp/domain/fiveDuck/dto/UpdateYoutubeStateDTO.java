package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.Mp3UseYnEnum;
import com.dev.webApp.util.YoutubeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateYoutubeStateDTO {
    Integer youtubeNo;
    YoutubeUseYnEnum youtubeUseYnEnum;
}
