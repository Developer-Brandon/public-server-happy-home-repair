package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.ContentsMadeNatureUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateContentsStateRequestDTO {
    Integer contentsNo;
    ContentsMadeNatureUseYnEnum contentsMadeNatureUseYnEnum;
}
