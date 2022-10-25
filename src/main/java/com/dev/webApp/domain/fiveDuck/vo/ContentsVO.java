package com.dev.webApp.domain.fiveDuck.vo;

import com.dev.webApp.util.ContentsMadeNatureUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentsVO {
    Integer madeNatureNo;
    String englishName;
    String koreanName;
    ContentsMadeNatureUseYnEnum contentsMadeNatureUseYnEnum;
    Date regDt;
}
