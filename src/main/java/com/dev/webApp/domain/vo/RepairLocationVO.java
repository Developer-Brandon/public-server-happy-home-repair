package com.dev.webApp.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RepairLocationVO {

    private Integer repairLocationNo;
    private String name;
    private String explanation;
    private Timestamp regDt;
    private Timestamp modDt;
}
