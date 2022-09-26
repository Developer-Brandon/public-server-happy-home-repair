package com.dev.webApp.mapper.notice;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.mapper.NoticeMapper;
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

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(1)
                .offset(1) // service단에서 처리해주는 처리를 대신...
                .build();

        noticeMapper
                .selectNoticePaginationList(selectNoticePaginationDTO)
                .forEach(notice -> System.out.println(notice.getTitle()));
    }

    @Test
    public void getNoticePaginationList() {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .offset(1)
                .pageSize(1) // itemCntOfSize의 뜻과 같은 뜻이라고 봐도 무방합니다
                .build();

        noticeMapper
                .selectNoticePaginationList(selectNoticePaginationDTO)
                .forEach(notice -> System.out.println(notice.getTitle()));

        ///////////////////////////////////////////////////////////

        Integer totalCnt = noticeMapper.getTotalCnt();

        System.out.println("총 아이템의 개수 : " + totalCnt);
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
        assertThat(noticeVO.getNoticeNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 삽입된 데이터로 조회

        Integer noticeNo = noticeVO.getNoticeNo();

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
        assertThat(noticeVO.getNoticeNo(), is(greaterThan(1)));
    }

    @Test
    public void updateNotice() {
        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(10)
                .title("업데이트_테스트_제목")
                .content("업데이트_테스트_내용")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        int updatedNoticeCnt = noticeMapper.updateNotice(noticeVO);

        System.out.println("updatedNoticeCnt" + updatedNoticeCnt);
    }

    @Test
    public void deleteNotice() {

        Integer noticeNo = 11;

        int deletedNoticeCnt = noticeMapper.deleteNotice(noticeNo);

        System.out.println("deletedNoticeCnt" + deletedNoticeCnt);
    }
}
