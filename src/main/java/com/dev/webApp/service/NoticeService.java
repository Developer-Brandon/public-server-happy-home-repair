package com.dev.webApp.service;

import com.dev.webApp.domain.NoticeVO;

import java.util.List;

public interface NoticeService {

    public boolean registerNotice(NoticeVO noticeVO) throws Exception;

    public boolean registerNoticeList(List<NoticeVO> noticeVOList) throws Exception;

    public boolean modifyNotice(NoticeVO noticeVO) throws Exception;

    public boolean modifyNoticeState(NoticeVO noticeVO) throws Exception;

    public boolean removeNotice(Long noticeNo) throws Exception;

    public NoticeVO getNotice(NoticeVO noticeVO);

    public List<NoticeVO> getNoticeList();

    public List<NoticeVO> getNoticeListBySize(Integer size);
}
