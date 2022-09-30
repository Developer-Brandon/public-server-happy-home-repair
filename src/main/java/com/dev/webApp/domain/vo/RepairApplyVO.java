package com.dev.webApp.domain.vo;

import com.dev.webApp.util.AgreeOrNotEnum;
import com.dev.webApp.util.NoticeUseYnEnum;
import com.dev.webApp.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairApplyVO {
    Integer repairApplyNo;
    Integer repairTypeNo;
    Integer repairLocationNo;
    Integer repairStateNo;
    UserTypeEnum userTypeEnum;
    String phoneNumber;
    AgreeOrNotEnum agreeOrNotEnum;
    String explanation;
    String regDt;
    String modDt;
}
