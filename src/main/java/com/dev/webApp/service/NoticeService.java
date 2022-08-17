package com.dev.webApp.service;

import com.dev.webApp.domain.NoticeVO;

import java.util.List;

public interface NoticeService {

    public boolean registerNotice(NoticeVO noticeVO);

    public boolean registerNoticeList(List<NoticeVO> noticeVOList);

    public boolean modifyNotice(NoticeVO noticeVO);

    public boolean removeNotice(Long noticeNo);

    public NoticeVO getNotice(NoticeVO noticeVO);

    public List<NoticeVO> getNoticeList();

    public List<NoticeVO> getNoticeListBySize(Integer size);
}
