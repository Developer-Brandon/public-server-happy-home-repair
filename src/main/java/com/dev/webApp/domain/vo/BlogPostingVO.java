package com.dev.webApp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostingVO {

    Integer postingNo;

    String title;

    String imgSrc;

    // todo: 추후 이동주소 추가하기
    // targetUrl

    String postingRegDt;

    String regDt;
}
