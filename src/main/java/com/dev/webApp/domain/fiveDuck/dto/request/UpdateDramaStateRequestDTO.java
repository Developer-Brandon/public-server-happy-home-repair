package com.dev.webApp.domain.fiveDuck.dto.request;

import com.dev.webApp.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateDramaStateRequestDTO {
    Integer dramaNo;
    DramaUseYnEnum dramaUseYnEnum;
}
