package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectRepairApplyDTO {

    @Builder.Default
    Integer itemCnt = 100;
}
