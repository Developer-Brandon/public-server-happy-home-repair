package com.dev.webApp.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class BlogVO {
    Integer postingNo;
    String title;
    String type;
    String imgSrc;
    Date postRegDt;
    Date regDt;
    Date modDt;
}
