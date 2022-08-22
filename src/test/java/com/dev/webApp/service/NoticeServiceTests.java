package com.dev.webApp.service;

import com.dev.webApp.domain.vo.NoticeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeServiceTests {

    // TODO: notice 서비스쪽부터 로직 추가 예정

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

        Long registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertNotNull(registerSuccessorNot);
    }
}
