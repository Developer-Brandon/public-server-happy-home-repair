package com.dev.webApp.domain.vo;

import com.dev.webApp.util.FaqUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqVO {

    Long faqNo;
    String title;
    String content;
    FaqUseYnEnum useYnEnum;

    // todo: 추후 dateTime관련 되서 처리할때. java에서 다루는 dateTime에 대해서 총 정리하고 비교해서 알아봐야 할듯
    // https://hianna.tistory.com/607
    Date regDt;
    Date modDt;
}
