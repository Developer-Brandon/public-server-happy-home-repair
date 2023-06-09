package com.dev.webApp.domain.vo;

import com.dev.webApp.util.FaqUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqVO {

    Integer faqNo;

    String title;

    String content;

    FaqUseYnEnum useYnEnum;

    String regDt;

    String modDt;
}
