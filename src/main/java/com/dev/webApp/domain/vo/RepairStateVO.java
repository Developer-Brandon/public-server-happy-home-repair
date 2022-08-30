package com.dev.webApp.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RepairStateVO {

    private Integer repairStateNo;
    private String name;
    private Timestamp regDt;
    private Timestamp modDt;
}
