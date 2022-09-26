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

    Integer noticeNo;

    String title;

    String content;

    NoticeUseYnEnum useYnEnum;

    Timestamp regDt;

    Timestamp modDt;
}
