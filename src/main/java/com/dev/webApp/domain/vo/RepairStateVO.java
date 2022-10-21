package com.dev.webApp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairStateVO {
    Integer repairStateNo;
    String name;
    String explanation;
    String regDt;
    String modDt;
}
