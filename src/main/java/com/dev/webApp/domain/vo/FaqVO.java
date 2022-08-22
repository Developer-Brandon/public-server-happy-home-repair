package com.dev.webApp.domain.vo;

import com.dev.webApp.util.FaqUseYnEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FaqVO {

    private Long faqNo;
    private String title;
    private String content;
    private FaqUseYnEnum useYnEnum;

    // todo: 추후 dateTime관련 되서 처리할때. java에서 다루는 dateTime에 대해서 총 정리하고 비교해서 알아봐야 할듯
    // https://hianna.tistory.com/607
    private Date regDt;
    private Date modDt;
}
