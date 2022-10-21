package com.dev.webApp.controller.notice;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.util.NoticeUseYnEnum;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// todo:
// 제대로된 test case에 대해서 작성하려면, transaction 처리를 이용하여 data를 전부 지운 후에...
// 제대로 번호를 확인하는 test를 강항해야 의미가 있을 듯 합니다.
// 그것도 아니라면 service단에서만 mocking을 이용하여 test case를 작성해야 겠죠.
// 하여 여기서는 간단하게 c-r-u-d에 대한 테스트만 강행하도록 하겠습니다.

@Transactional
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class NoticeControllerPaginationTests extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectNoticeListPagination() throws Exception {

        // 1. 리스트를 조회합니다.

        // given
        String url2 = "/notice/list";

        // when
        ResultActions resultActions2 = mockMvc
                .perform(
                        get(url2)
                            .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectNoticeListPagination2() throws Exception {

        // given
        String pageNo = "2";

        String url = "/notice/list?currentPage=" + pageNo;

        // when
        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectNoticeListPagination3() throws Exception {

        // given
        String pageSize = "30";

        String url = "/notice/list?currentPage=1" + "&" + "pageSize=" + pageSize;

        // when
        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectNoticeListPagination4() throws Exception {

        // given
        String pageNo = "2";

        String pageSize = "20";

        String url = "/notice/list?currentPage=" + pageNo + "&" + "pageSize=" + pageSize;

        // when
        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectNotice() throws Exception {

        // given
        String pageNo = "1";

        String url = "/notice?faqNo=" + pageNo;

        // when
        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void insertNotice() throws Exception {

        // given
        String url = "/notice";

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트_공지사항_제목")
                .content("테스트_내용_제목")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        String insertNoticeDTO = new Gson().toJson(noticeVO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertNoticeDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void updateNotice() throws Exception {

        // given
        String url = "/notice";

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(1)
                .title("테스트_공지사항_제목")
                .content("테스트_내용_제목")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        String updateNoticeDTO = new Gson().toJson(noticeVO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateNoticeDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void updateNoticeState() throws Exception {

        // given
        String url = "/notice/state";

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(1)
                .title("테스트_공지사항_제목")
                .content("테스트_내용_제목")
                .useYnEnum(NoticeUseYnEnum.N)
                .build();

        String updateNoticeStateDTO = new Gson().toJson(noticeVO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateNoticeStateDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void deleteNotice() throws Exception {

        // given
        String url = "/notice?faqNo=2";

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        delete(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk());
    }
}
