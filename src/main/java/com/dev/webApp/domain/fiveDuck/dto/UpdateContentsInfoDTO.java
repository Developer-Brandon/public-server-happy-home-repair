package com.dev.webApp.domain.fiveDuck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateContentsInfoDTO {
    Integer contentsNo;
    String englishName;
    String koreanName;
}
