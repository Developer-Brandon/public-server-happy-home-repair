package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.YoutubeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateYoutubeStateRequestDTO {
    Integer youtubeNo;
    YoutubeUseYnEnum youtubeUseYnEnum;
}
