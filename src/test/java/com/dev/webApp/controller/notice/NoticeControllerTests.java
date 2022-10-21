package com.dev.webApp.controller.notice;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.util.NoticeUseYnEnum;
import com.dev.webApp.util.NumberUtil;
import com.google.gson.Gson;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@Log4j
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class NoticeControllerTests extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private NumberUtil numberUtil;

    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(NoticeControllerTests.class);

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void insertNoticeAndSelectNoticeListTest() throws Exception {

        // notice list가 제대로 조회되는지 테스트 하기

        // 1. 먼저, notice를 삽입하기

        // given
        String url = "/notice";

        int randomInteger = numberUtil.getRandomNumber();

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
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

        /////////////////////////////////////////////////////////

        // 2. 그 후, notice list 조회해서 방금 전 삽입한 notice가 제대로 삽입되었는지 확인하기
        // (단일 조회는 아래에서 테스트 예정입니다)

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
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
                .andExpect(jsonPath("$.[0].title").exists())
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].title", is(containsString("테스트" + randomInteger))))
                .andExpect(jsonPath("$.[0].content").exists())
                .andExpect(jsonPath("$.[0].content").isString())
                .andExpect(jsonPath("$.[0].content", is(containsString("테스트" + randomInteger))));
    }

    @Test
    public void insertAndSelectNoticeTest() throws Exception {

        // 단일 notice가 조회되는지 확인하기

        // 1. 먼저, notice를 삽입하기

        // given
        String url = "/notice";

        int randomInteger = numberUtil.getRandomNumber();

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
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

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////////

        // 2. 단일 notice 조회로 검증

        // given
        String noticeNo = mvcResultToString;

        String url2 = "/notice?noticeNo=" + noticeNo;

        // when
        ResultActions resultActions2 = mockMvc
                .perform(get(url2))
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.title", is(containsString("테스트" + randomInteger))))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.content", is(containsString("테스트" + randomInteger))));
    }

    @Test
    public void insertNotice() throws Exception {

       // 위의 테스트에서 검증했으므로 추가 검증은 하지 않도록 하겠습니다.
    }

    @Test
    public void updateNotice() throws Exception {

        // 삽입한 notice를 수정한 후, 제대로 조회가 되는지 확인

        // 1. 먼저, notice를 삽입하기

        // given
        String url = "/notice";

        int randomInteger = numberUtil.getRandomNumber();

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
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

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////////

        // 2. 수정하기

        NoticeVO updateNoticeVO = NoticeVO.builder()
                .noticeNo(Integer.valueOf(mvcResultToString))
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
                .useYnEnum(NoticeUseYnEnum.Y)
                .build();

        String updateNoticeDTO = new Gson().toJson(updateNoticeVO);

        ResultActions resultActions2 = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateNoticeDTO)
                )
                .andDo(print());

        resultActions2
                .andExpect(status().is(200));

        /////////////////////////////////////////////////////////

        // 3. 단일 조회로 수정된 데이터 검증

        // given
        String url3 = "/notice?noticeNo=" + mvcResultToString;

        // when
        ResultActions resultActions3 = mockMvc
                .perform(get(url3))
                .andDo(print());

        // then
        resultActions3
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.title", is(containsString("테스트" + randomInteger))))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").isString())
                .andExpect(jsonPath("$.content", is(containsString("테스트" + randomInteger))));
    }

    @Test
    public void updateNoticeState() throws Exception {

        // 삽입한 notice의 상태를 수정한 후, 제대로 조회가 되는지 확인

        // 1. 먼저, notice를 삽입하기

        // given
        String url = "/notice";

        int randomInteger = numberUtil.getRandomNumber();

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
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

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////////

        // 2. 상태 수정하기

        // given
        String url2 = "/notice/state";

        NoticeVO updateStateNoticeVO = NoticeVO.builder()
                .noticeNo(Integer.valueOf(mvcResultToString))
                .useYnEnum(NoticeUseYnEnum.N)
                .build();

        String updateNoticeStateDTO = new Gson().toJson(updateStateNoticeVO);

        // when
        ResultActions resultActions2 = mockMvc
                .perform(
                        put(url2)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(updateNoticeStateDTO)
                )
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200));

        /////////////////////////////////////////////////////////

        // 3. 단일 조회로 수정된 데이터 검증

        // given
        String url3 = "/notice?noticeNo=" + mvcResultToString;

        // when
        ResultActions resultActions3 = mockMvc
                .perform(get(url3))
                .andDo(print());

        // then
        resultActions3
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
                .andExpect(jsonPath("$.useYnEnum").exists())
                .andExpect(jsonPath("$.useYnEnum").isString())
                .andExpect(jsonPath("$.useYnEnum", is("N")));

    }

    @Test
    public void deleteAndSelectNotice() throws Exception {

        // 삽입한 notice의 상태를 수정한 후, 제대로 조회가 되는지 확인

        // 1. 먼저, notice를 삽입하기

        // given
        String url = "/notice";

        int randomInteger = numberUtil.getRandomNumber();

        NoticeVO noticeVO = NoticeVO.builder()
                .title("테스트" + randomInteger + "_공지사항_제목")
                .content("테스트" + randomInteger + "_공지사항_내용")
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

        MvcResult mvcResult = resultActions.andReturn();

        String mvcResultToString = mvcResult.getResponse().getContentAsString();

        logger.info("mvcResultToString: {}", mvcResultToString);

        /////////////////////////////////////////////////////

        // 2. 그 후 삭제합니다

        String url2 = "/notice?noticeNo=" + mvcResultToString;

        ResultActions resultActions2 = mockMvc
                .perform(
                        delete(url2)
                            .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        resultActions2
                .andExpect(status().is(200));

        /////////////////////////////////////////////////////

        // 3. 삭제가 제대로 되었는지 단일 조회로 검증합니다.

        // given
        String url3 = "/notice?noticeNo=" + mvcResultToString;

        // when
        ResultActions resultActions3 = mockMvc
                .perform(get(url3))
                .andDo(print());

        // then
        resultActions3
                .andExpect(status().is(200))
                .andExpect(result -> assertNotNull(result.getResolvedException()));
    }
}
