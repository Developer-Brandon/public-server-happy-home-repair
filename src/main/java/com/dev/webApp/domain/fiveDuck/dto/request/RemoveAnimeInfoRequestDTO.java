package com.dev.webApp.domain.fiveDuck.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RemoveAnimeInfoRequestDTO {
    Integer animeNo;
}