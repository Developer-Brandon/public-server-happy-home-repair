package com.dev.webApp.service;

import com.dev.webApp.domain.NoticeVO;
import com.dev.webApp.mapper.NoticeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
    public void serviceRegisterTest() {

        NoticeVO noticeVO = new NoticeVO();

        noticeVO.setTitle("새로 작성하는 글");
        noticeVO.setContent("새로 작성하는 내용");

        Boolean registerSuccessorNot = noticeService.registerNotice(noticeVO);

        assertFalse(registerSuccessorNot);
    }
}
