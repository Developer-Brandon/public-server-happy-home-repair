package com.dev.webApp.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVO {
    private Long noticeNo;
    private String title;
    private String content;
    private Date regDt;
    private Date modDt;
}
