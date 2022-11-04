package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;
import com.dev.webApp.mapper.NoticeMapper;
import com.dev.webApp.util.page.PageHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    private final NoticeMapper noticeMapper;

    @Override
    public PaginationNoticeVO getNoticePaginationList(SelectNoticePaginationDTO selectNoticePaginationDTO) throws Exception {

        int totalCnt = noticeMapper.getTotalCnt();

        PageHandler pageHandler = new PageHandler(totalCnt, selectNoticePaginationDTO.getCurrentPage());

        // 만약 현재의 페이지가.. 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 3 대로 오프셋 설정
        Integer offset = selectNoticePaginationDTO.getCurrentPage() - 1;

        // PageSize는 DTO에서 기본으로 10으로 처리되어 있습니다.
        selectNoticePaginationDTO.setOffset(offset * selectNoticePaginationDTO.getPageSize());

        selectNoticePaginationDTO.setPageSize(selectNoticePaginationDTO.getPageSize());

        List<NoticeVO> noticeVOList = noticeMapper.selectNoticePaginationList(selectNoticePaginationDTO);

        return PaginationNoticeVO.builder()
                .noticeVOList(noticeVOList)
                .pageHandler(pageHandler)
                .build();
    }

    @Override
    public NoticeVO getNotice(NoticeVO noticeVO) throws Exception {

        NoticeVO selectedNoticeVO = noticeMapper.selectNotice(noticeVO.getNoticeNo());

        if (selectedNoticeVO == null) {
            throw new Exception("게시물을 조회하는 과정에서 문제가 발생하였습니다.");
        }

        return selectedNoticeVO;
    }

    @Override
    public Integer registerNotice(NoticeVO noticeVO) throws Exception {

        if (noticeMapper.insertNotice(noticeVO) != 1) {
            throw new Exception("게시물을 삽입하는 과정에서 문제가 발생하였습니다.");
        }

        // 반환값으로 삽입된 게시물의 번호를 반환합니다.
        return noticeVO.getNoticeNo();
    }

    @Override
    public void modifyNotice(NoticeVO noticeVO) throws Exception {

        if (noticeMapper.updateNotice(noticeVO) != 1) {
            throw new Exception("게시물을 수정하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public void modifyNoticeState(NoticeVO noticeVO) throws Exception {

        if (noticeMapper.updateNoticeState(noticeVO) != 1) {
            throw new Exception("게시물의 상태를 수정하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public void removeNotice(Integer noticeNo) throws Exception {

        if (noticeMapper.deleteNotice(noticeNo) != 1) {
            throw new Exception("게시물을 삭제하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public Integer getTotalCnt() throws Exception {
        return noticeMapper.getTotalCnt();
    }
}
