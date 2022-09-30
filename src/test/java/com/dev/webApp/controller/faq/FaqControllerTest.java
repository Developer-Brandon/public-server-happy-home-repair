package com.dev.webApp.controller.faq;

import com.dev.webApp.config.BaseConfigController;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.service.FaqService;
import com.dev.webApp.util.FaqUseYnEnum;
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
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// todo: 추후 트렌젝션처리 및 응답결과를 세세하게 처리
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class FaqControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private FaqService faqService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectFaqList() throws Exception {

        String url = "/faq/list";

        ResultActions resultActions = mockMvc
                .perform(
                        get(url)
                        .contentType(BaseConfigController.JSON_FORMAT)
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectFaqList2() throws Exception {

        String pageNo = "2";

        String url = "/faq/list?currentPage=" + pageNo;

        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        resultActions
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
