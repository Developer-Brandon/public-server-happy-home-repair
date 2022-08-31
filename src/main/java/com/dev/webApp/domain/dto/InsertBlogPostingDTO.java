package com.dev.webApp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InsertBlogPostingDTO {
    Integer insertedPostingNo;
    Integer postingTypeNo;
    String postingTitle;
    String postingImageSrc;
    String postingRegDt;
}
