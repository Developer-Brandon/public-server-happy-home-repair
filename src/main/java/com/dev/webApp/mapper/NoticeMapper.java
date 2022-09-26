package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;

import java.util.List;

public interface NoticeMapper {

    List<NoticeVO> selectNoticePaginationList(SelectNoticePaginationDTO noticePaginationDTO);

    NoticeVO selectNotice(Integer noticeNo);

    int insertNotice(NoticeVO noticeVO);

    int updateNotice(NoticeVO noticeNo);

    int updateNoticeState(NoticeVO noticeVO);

    int deleteNotice(Integer noticeNo);

    int getTotalCnt();
}
