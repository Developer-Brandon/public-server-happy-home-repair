package com.dev.webApp.domain.fiveDuck.dto;

import com.dev.webApp.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateDramaStateDTO {
    Integer dramaNo;
    DramaUseYnEnum dramaUseYnEnum;
}
