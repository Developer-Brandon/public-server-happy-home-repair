package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.util.NoticeUseYnEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeMapperTests {

    @Autowired
    private NoticeMapper noticeMapper;

    @Before
    public void init() {
    }

    @Test
    public void getNoticeList() {

        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .build();

        noticeMapper
                .selectNoticeList(selectNoticeDTO)
                .forEach(notice -> System.out.println(notice.getTitle()));
    }

    @Test
    public void getNotice() {

        // 1. 조회 전 삽입

        NoticeVO noticeVO = NoticeVO.builder()
                .title("삽입_테스트_제목")
                .content("삽입_테스트_내용")
                .build();

        Boolean insertedNoticeCnt = noticeMapper.insertNotice(noticeVO) == 1;

        assertThat(insertedNoticeCnt, is(true));
        assertThat(noticeVO.getNoticeNo(), is(greaterThan(1L)));

        ///////////////////////////////////////////////////////////

        // 2. 삽입된 데이터로 조회

        Long noticeNo = noticeVO.getNoticeNo();

        NoticeVO noticeVO2 = noticeMapper.selectNotice(noticeNo);

        assertThat(noticeVO2, is(notNullValue()));
    }

    @Test
    public void insertNotice() {

        // 1. 삽입 테스트

        NoticeVO noticeVO = NoticeVO.builder()
                .title("삽입_테스트_제목")
                .content("삽입_테스트_내용")
                .build();

        Boolean insertedOrNot = noticeMapper.insertNotice(noticeVO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(noticeVO.getNoticeNo(), is(greaterThan(1L)));
    }

    @Test
    public void updateNotice() {
        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(10L)
                .title("업데이트_테스트_제목")
                .content("업데이트_테스트_내용")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        int updatedNoticeCnt = noticeMapper.updateNotice(noticeVO);

        System.out.println("updatedNoticeCnt" + updatedNoticeCnt);
    }

    @Test
    public void deleteNotice() {

        Long noticeNo = 11L;

        int deletedNoticeCnt = noticeMapper.deleteNotice(noticeNo);

        System.out.println("deletedNoticeCnt" + deletedNoticeCnt);
    }
}
