package com.dev.webApp.controller.faq;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.util.FaqUseYnEnum;
import com.dev.webApp.util.NumberUtil;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class FaqControllerPaginationTests extends TestCase {

    // Faq의 pagination을 test하기 위한 test case들입니다.

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private NumberUtil numberUtil;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void insertFaqAndSelectFaqListTest() throws Exception {

        // faq list가 제대로 조회되는지 테스트 하기

        // 1. 먼저, faq를 삽입하기

        String url = "/faq";

        int randomInteger = numberUtil.getRandomNumber();

        FaqVO faqVO = FaqVO.builder()
                .title("테스트" + randomInteger + "_자주하는질문_제목")
                .content("테스트" + randomInteger + "_자주하는질문_내용")
                .build();

        String insertFaqDTO = new Gson().toJson(faqVO);

        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertFaqDTO)
                )
                .andDo(print());

        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        /////////////////////////////////////////////////////////

        // 2. 그 후, faq list 조회해서 방금 전 삽입한 faq가 제대로 삽입되었는지 확인하기
        // (단일 조회는 아래에서 테스트 예정입니다)

        String url2 = "/faq/list";

        ResultActions resultActions2 = mockMvc
                .perform(
                        get(url2)
                        .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

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
    public void insertAndSelectFaqTest() throws Exception {

        // 단일 faq가 조회되는지 확인하기

        // 1. 먼저, faq를 삽입하기

        String url = "/faq";

        int randomInteger = numberUtil.getRandomNumber();

        FaqVO faqVO = FaqVO.builder()
                .title("테스트" + randomInteger + "_자주하는질문_제목")
                .content("테스트" + randomInteger + "_자주하는질문_내용")
                .build();

        String insertFaqDTO = new Gson().toJson(faqVO);

        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertFaqDTO)
                )
                .andDo(print());

        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));

        /////////////////////////////////////////////////////////

        // 2. 단일 faq 조회하기

        String pageNo = "2";

        String url2 = "/faq/list?currentPage=" + pageNo;

        ResultActions resultActions2 = mockMvc
                .perform(get(url2))
                .andDo(print());

        resultActions2
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectFaqList3() throws Exception {

        String pageSize = "20";

        String url = "/faq/list?currentPage=1" + "&" + "pageSize=" + pageSize;

        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectFaqList4() throws Exception {

        String pageNo = "2";

        String pageSize = "20";

        String url = "/faq/list?currentPage=" + pageNo + "&" + "pageSize=" + pageSize;

        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectFaq() throws Exception {

        String pageNo = "1";

        String url = "/faq?faqNo=" + pageNo;

        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    public void insertFaq() throws Exception {

        String url = "/faq";

        FaqVO faqVO = FaqVO.builder()
                .title("테스트_자주하는질문_제목")
                .content("테스트_자주하는질문_내용")
                .build();

        String insertFaqDTO = new Gson().toJson(faqVO);

        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(insertFaqDTO)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void updateFaq() throws Exception {

        String url = "/faq";

        FaqVO faqVO = FaqVO.builder()
                .faqNo(1)
                .title("테스트_자주하는질문_제목")
                .content("테스트_자주하는질문_내용")
                .useYnEnum(FaqUseYnEnum.Y)
                .build();

        String updateFaqDTO = new Gson().toJson(faqVO);

        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(updateFaqDTO)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    public void updateFaqState() throws Exception {

        String url = "/faq/state";

        FaqVO faqVO = FaqVO.builder()
                .faqNo(1)
                .title("테스트_자주하는질문_제목")
                .content("테스트_자주하는질문_내용")
                .useYnEnum(FaqUseYnEnum.N)
                .build();

        String updateFaqStateDTO = new Gson().toJson(faqVO);

        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(updateFaqStateDTO)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFaq() throws Exception {

        String url = "/faq?faqNo=2";

        ResultActions resultActions = mockMvc
                .perform(
                        delete(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk());
    }
}
