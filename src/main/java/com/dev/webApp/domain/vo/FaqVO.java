package com.dev.webApp.domain.vo;

import com.dev.webApp.util.FaqUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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

    Timestamp regDt;

    Timestamp modDt;
}
