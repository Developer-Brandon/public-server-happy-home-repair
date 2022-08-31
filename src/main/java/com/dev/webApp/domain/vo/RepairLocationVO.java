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

    private Integer repairLocationNo;
    private String name;
    private String explanation;
    private Timestamp regDt;
    private Timestamp modDt;
}
