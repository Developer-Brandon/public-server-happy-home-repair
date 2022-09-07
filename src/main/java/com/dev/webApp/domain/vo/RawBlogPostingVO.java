package com.dev.webApp.domain.vo;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawBlogPostingVO {

    @Builder.Default
    Integer postingTypeNo = 1;

    String title;

    String imgSrc;

    String postingRegDt;
}
