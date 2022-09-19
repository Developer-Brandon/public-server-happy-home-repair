package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;

import java.util.List;

public interface NoticeService {

    Long registerNotice(NoticeVO noticeVO) throws Exception;

    void registerNoticeList(List<NoticeVO> noticeVOList) throws Exception;

    void modifyNotice(NoticeVO noticeVO) throws Exception;

    void modifyNoticeState(NoticeVO noticeVO) throws Exception;

    void removeNotice(Long noticeNo) throws Exception;

    NoticeVO getNotice(NoticeVO noticeVO) throws Exception;

    List<NoticeVO> getNoticeList(SelectNoticeDTO selectNoticeDTO) throws Exception;

    PaginationNoticeVO getNoticePaginationList(SelectNoticePaginationDTO selectNoticePaginationDTO) throws Exception;

    Integer getTotalCnt() throws Exception;
}
