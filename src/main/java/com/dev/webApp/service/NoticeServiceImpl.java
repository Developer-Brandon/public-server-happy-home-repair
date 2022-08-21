package com.dev.webApp.service;

import com.dev.webApp.domain.NoticeVO;
import com.dev.webApp.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Override
    public boolean registerNotice(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.insertNotice(noticeVO) != 1) {
            throw new Exception();
        }

        return true;
    }

    @Override
    public boolean registerNoticeList(List<NoticeVO> noticeVOList) throws Exception {

        // 게시물 리스트 정보를 통째로 등록하는 메소드입니다.
        if(noticeMapper.insertNoticeList(noticeVOList) == 0) {
            throw new Exception();
        }

        return true;
    }

    @Override
    public boolean modifyNotice(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.updateNotice(noticeVO) != 1) {
            throw new Exception();
        }

        return true;
    }

    @Override
    public boolean modifyNoticeState(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.updateNoticeState(noticeVO) != 1) {
            throw new Exception();
        }

        return true;
    }

    @Override
    public boolean removeNotice(Long noticeNo) throws Exception {

        if(noticeMapper.deleteNotice(noticeNo) != 1) {
            throw new Exception();
        }

        return true;
    }

    @Override
    public NoticeVO getNotice(NoticeVO noticeVO) {

        return noticeMapper.selectNotice(noticeVO.getNoticeNo());
    }

    @Override
    public List<NoticeVO> getNoticeList() {

        return noticeMapper.selectNoticeList();
    }

    @Override
    public List<NoticeVO> getNoticeListBySize(Integer size) {

        return noticeMapper.selectNoticeListBySize(size);
    }
}
