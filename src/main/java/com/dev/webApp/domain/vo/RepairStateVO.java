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
public class RepairStateVO {
    Integer repairStateNo;
    String name;
    String explanation;
    Timestamp regDt;
    Timestamp modDt;
}
