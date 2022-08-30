package com.dev.webApp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
public class RepairTypeVO {

    private Integer repairTypeNo;
    private Integer repairTypeOrder;
    private String title;
    private String explanation;
    private Timestamp regDt;
    private Timestamp modDt;
}
