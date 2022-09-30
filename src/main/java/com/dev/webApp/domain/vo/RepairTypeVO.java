package com.dev.webApp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairTypeVO {
    Integer repairTypeNo;
    Integer repairTypeOrder;
    String title;
    String explanation;
    String regDt;
    String modDt;
}
