package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SelectRepairTypeDTO {

    @Builder.Default
    Integer itemCnt = 10;
}
