package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.AnimeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateAnimeStateRequestDTO {
    Integer animeNo;
    AnimeUseYnEnum animeUseYnEnum;
}
