package com.dev.webApp.domain.vo;

import com.dev.webApp.util.NoticeUseYnEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
    Long noticeNo;

    String title;

    String content;

    @Builder.Default
    NoticeUseYnEnum useYnEnum = NoticeUseYnEnum.Y;

    Timestamp regDt;

    Timestamp modDt;
}
