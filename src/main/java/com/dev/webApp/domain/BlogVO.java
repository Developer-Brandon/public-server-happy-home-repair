package com.dev.webApp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class BlogVO {

    private Integer postingNo;
    private String title;
    private String type;
    private String imgSrc;
    private Date postRegDt;
    private Date regDt;
    private Date modDt;
}
