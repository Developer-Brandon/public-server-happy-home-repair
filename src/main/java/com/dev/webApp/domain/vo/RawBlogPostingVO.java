package com.dev.webApp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
