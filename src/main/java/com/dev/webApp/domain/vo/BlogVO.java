package com.dev.webApp.domain.vo;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {
    Integer postingNo;
    String title;
    String content;
    String imgSrc;
    Date postingRegDt;
    Date regDt;
}
