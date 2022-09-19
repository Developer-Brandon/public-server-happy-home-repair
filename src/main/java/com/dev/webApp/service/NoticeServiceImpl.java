package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;
import com.dev.webApp.mapper.NoticeMapper;
import com.dev.webApp.util.page.PageHandler;
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
    public Long registerNotice(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.insertNotice(noticeVO) != 1) {
            throw new Exception();
        }

        // 반환값으로 삽입된 게시물의 번호를 반환합니다.
        return noticeVO.getNoticeNo();
    }

    @Override
    public void registerNoticeList(List<NoticeVO> noticeVOList) throws Exception {

        // 게시물 리스트 정보를 통째로 등록하는 메소드입니다.
        if(noticeMapper.insertNoticeList(noticeVOList) == 0) {
            throw new Exception();
        }
    }

    @Override
    public NoticeVO getNotice(NoticeVO noticeVO) throws Exception {

        NoticeVO selectedNoticeVO = noticeMapper.selectNotice(noticeVO.getNoticeNo());

        if(selectedNoticeVO.getNoticeNo() == 0L) {
            throw new Exception();
        }

        return selectedNoticeVO;
    }

    @Override
    public List<NoticeVO> getNoticeList(SelectNoticeDTO selectNoticeDTO) throws Exception{

        return noticeMapper.selectNoticeList(selectNoticeDTO);
    }

    @Override
    public PaginationNoticeVO getNoticePaginationList(SelectNoticePaginationDTO selectNoticePaginationDTO) throws Exception {

        Integer totalCnt = noticeMapper.getTotalCnt();

        PageHandler pageHandler = new PageHandler(totalCnt, selectNoticePaginationDTO.getCurrentPage());

        selectNoticePaginationDTO.setOffset(Math.max(selectNoticePaginationDTO.getCurrentPage()-1, 1) * selectNoticePaginationDTO.getPageSize());
        System.out.println("offset: " + selectNoticePaginationDTO.getOffset());

        selectNoticePaginationDTO.setPageSize(pageHandler.getNAV_SIZE());
        System.out.println("pageSize: " + selectNoticePaginationDTO.getPageSize());


        return PaginationNoticeVO.builder()
                .noticeVOList(noticeMapper.selectNoticePaginationList(selectNoticePaginationDTO))
                .pageHandler(pageHandler)
                .build();
    }

    @Override
    public void modifyNotice(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.updateNotice(noticeVO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void modifyNoticeState(NoticeVO noticeVO) throws Exception {

        if(noticeMapper.updateNoticeState(noticeVO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeNotice(Long noticeNo) throws Exception {

        if(noticeMapper.deleteNotice(noticeNo) != 1) {
            throw new Exception();
        }
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return noticeMapper.getTotalCnt()
                ;
    }
}
