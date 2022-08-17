package com.dev.webApp.service;

import com.dev.webApp.domain.NoticeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService{

    @Override
    public boolean registerNotice(NoticeVO noticeVO) {
        return false;
    }

    @Override
    public boolean registerNoticeList(List<NoticeVO> noticeVOList) {
        return false;
    }

    @Override
    public boolean modifyNotice(NoticeVO noticeVO) {
        return false;
    }

    @Override
    public boolean removeNotice(Long noticeNo) {
        return false;
    }

    @Override
    public NoticeVO getNotice(NoticeVO noticeVO) {
        return null;
    }

    @Override
    public List<NoticeVO> getNoticeList() {
        return null;
    }

    @Override
    public List<NoticeVO> getNoticeListBySize(Integer size) {
        return null;
    }
}
