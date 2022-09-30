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
public class RepairLocationVO {
    Integer repairLocationNo;
    Integer repairLocationOrder;
    String explanation;
    String name;
    String regDt;
    String modDt;
}
