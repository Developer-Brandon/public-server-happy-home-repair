package com.dev.webApp.domain.dto;

import com.dev.webApp.util.AgreeOrNotEnum;
import com.dev.webApp.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertBlogPostingDTO {
    Integer insertedRepairApplyNo;
    Integer repairTypeNo;
    Integer repairLocationNo;
    Integer repairStateNo;
    UserTypeEnum userTypeEnum;
    String phoneNumber;
    AgreeOrNotEnum agreeOrNotEnum;
    String explanation;
}
