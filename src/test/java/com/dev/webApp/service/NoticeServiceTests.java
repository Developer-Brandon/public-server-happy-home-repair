package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeServiceTests {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void serviceExistTest() {
        assertNotNull(noticeService);

    }

    @Test
    public void serviceRegisterTest() throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로 작성하는 글")
                .content("새로 작성하는 내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);
    }

    @Test
    public void serviceGetNoticeListTest() throws Exception {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(1)
                .build();

        List<NoticeVO> noticeVOList = noticeService
                .getNoticePaginationList(selectNoticePaginationDTO)
                .getNoticeVOList();

        assertNotNull(noticeVOList);
    }
}
