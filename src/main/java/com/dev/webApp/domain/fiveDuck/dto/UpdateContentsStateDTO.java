package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.ContentsMadeNatureUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateContentsStateDTO {
    Integer contentsNo;
    ContentsMadeNatureUseYnEnum contentsMadeNatureUseYnEnum;
}
