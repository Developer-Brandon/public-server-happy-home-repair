package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;

public interface NoticeService {

    PaginationNoticeVO getNoticePaginationList(SelectNoticePaginationDTO selectNoticePaginationDTO) throws Exception;

    NoticeVO getNotice(NoticeVO noticeVO) throws Exception;

    Integer registerNotice(NoticeVO noticeVO) throws Exception;

    void modifyNotice(NoticeVO noticeVO) throws Exception;

    void modifyNoticeState(NoticeVO noticeVO) throws Exception;

    void removeNotice(Integer noticeNo) throws Exception;

    Integer getTotalCnt() throws Exception;
}
