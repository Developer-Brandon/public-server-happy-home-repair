package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.util.NoticeUseYnEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
    public void selectWholeCount() throws Exception {

        for(Integer i = 0; i < 10; i++) {

            NoticeVO noticeVO = NoticeVO.builder()
                    .title("새로_작성하는_글")
                    .content("새로_작성하는_내용")
                    .build();

            Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

            assertNotNull(registerSuccessorNot);
        }

        ////////////////////////////////////////////////

        Integer totalCount = noticeService.getTotalCnt();

        assertThat(totalCount, is(greaterThan(1)));
    }

    @Test
    public void serviceRegisterTest() throws Exception {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);
    }

    @Test
    public void serviceUpdateTests() throws Exception {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        NoticeVO noticeVO2 = NoticeVO.builder()
                .noticeNo(noticeVO.getNoticeNo())
                .title("새로_수정하는_글")
                .content("새로_수정하는_내용")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        noticeService.modifyNotice(noticeVO2);
    }

    @Test
    public void serviceUpdateStateTests() throws Exception {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        NoticeVO noticeVO2 = NoticeVO.builder()
                .noticeNo(noticeVO.getNoticeNo())
                .title("새로_수정하는_글")
                .content("새로_수정하는_내용")
                .useYnEnum(NoticeUseYnEnum.N)
                .build();

        noticeService.modifyNotice(noticeVO2);
    }

    @Test
    public void serviceRemoveTests() throws Exception {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        noticeService.removeNotice(noticeVO.getNoticeNo());
    }

    @Test
    public void serviceGetNoticeListTest() throws Exception {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);

        //////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(1)
                .build();

        List<NoticeVO> noticeVOList = noticeService
                .getNoticePaginationList(selectNoticePaginationDTO)
                .getNoticeVOList();

        assertNotNull(noticeVOList);
    }
}
