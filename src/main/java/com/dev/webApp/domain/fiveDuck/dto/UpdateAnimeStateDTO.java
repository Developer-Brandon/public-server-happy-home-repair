package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.AnimeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateAnimeStateDTO {
    Integer animeNo;
    AnimeUseYnEnum animeUseYnEnum;
}
