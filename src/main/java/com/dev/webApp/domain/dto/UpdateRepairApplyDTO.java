package com.dev.webApp.domain.dto;

import com.dev.webApp.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRepairApplyDTO {
    Integer repairApplyNo;
    Integer repairTypeNo;
    Integer repairLocationNo;
    Integer repairStateNo;
    UserTypeEnum userTypeEnum;
    String phoneNumber;
    String explanation;
}
