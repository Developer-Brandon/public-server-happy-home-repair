package com.dev.webApp.domain.dto;

import com.dev.webApp.util.AgreeOrNotEnum;
import com.dev.webApp.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateRepairApplyDTO {
    Integer repairApplyNo;
    Integer repairTypeNo;
    Integer repairLocationNo;
    Integer repairStateNo;
    UserTypeEnum userTypeEnum;
    String phoneNumber;
    String explanation;
}
