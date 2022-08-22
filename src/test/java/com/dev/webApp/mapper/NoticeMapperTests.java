package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.InsertNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.mysql.cj.protocol.x.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeMapperTests {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void getNoticeList() {

        InsertNoticeDTO insertNoticeDTO = InsertNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .build();

        noticeMapper
                .selectNoticeList(insertNoticeDTO)
                .forEach(notice -> System.out.println(notice.getTitle()));
    }

    @Test
    public void getNotice() {
        Long noticeNo = 1L;

        NoticeVO noticeVO = noticeMapper.selectNotice(noticeNo);

        System.out.println("noticeVO" + noticeVO);
    }

    @Test
    public void insertNotice() {

        NoticeVO noticeVO = NoticeVO.builder()
                .title("삽입_테스트_제목")
                .content("삽입_테스트_내용")
                .build();

        int insertedNoticeCnt = noticeMapper.insertNotice(noticeVO);

        System.out.println("insertedNoticeCnt" + insertedNoticeCnt);

        System.out.println("insertedNoticeNo" + noticeVO.getNoticeNo());
    }

    @Test
    public void updateNotice() {
        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(10L)
                .title("업데이트_테스트_제목")
                .content("업데이트_테스트_내용")
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
