package com.dev.webApp.mapper;

import com.dev.webApp.domain.NoticeVO;

import java.util.List;

public interface NoticeMapper {

    List<NoticeVO> selectNoticeList();

    List<NoticeVO> selectNoticeListBySize(Integer size);

    NoticeVO selectNotice(Long noticeNo);

    int insertNotice(NoticeVO noticeVO);

    int insertNoticeList(List<NoticeVO> noticeVOList);

    int updateNotice(NoticeVO noticeNo);

    int updateNoticeState(NoticeVO noticeVO);

    int deleteNotice(Long noticeNo);
}
