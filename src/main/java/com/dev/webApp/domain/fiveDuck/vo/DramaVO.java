package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.DramaUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DramaVO {
    Integer dramaNo;
    Integer madeNatureNo;
    String dramaTitle;
    String dramaAuthor;
    Integer pagePerDramaCnt;
    DramaUseYnEnum dramaUseYnEnum;
    Date dramaRegDt;
    Date regDt;
}
