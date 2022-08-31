package com.dev.webApp.domain.vo;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostingVO {
    Integer postingNo;
    String title;
    String imgSrc;
    Date postingRegDt;
    Date regDt;
}
