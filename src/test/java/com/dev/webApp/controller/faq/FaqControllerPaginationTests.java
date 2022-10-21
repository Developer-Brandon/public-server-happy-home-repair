package com.dev.webApp.controller.faq;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.util.FaqUseYnEnum;
import com.dev.webApp.util.TestUtil;
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
public class FaqControllerPaginationTests extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private TestUtil testUtil;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectFaqListPagination() throws Exception {

        // faq list를 호출합니다.
        // 아무런 파라미터가 없을때 어떤식으로 조회가 되는지 조회합니다.

        // 1. 대량의 faq를 삽입합니다.

        //        String url = "/faq";
        //
        //        for(Integer i = 0; i < 40; i++) {
        //
        //            FaqVO faqVO = FaqVO.builder()
        //                    .title("테스트_자주하는질문_제목" + i)
        //                    .content("테스트_자주하는질문_내용" + i)
        //                    .build();
        //
        //            String insertFaqDTO = new Gson().toJson(faqVO);
        //
        //            ResultActions resultActions = mockMvc
        //                    .perform(
        //                            post(url)
        //                                    .contentType(BaseConfigController.JSON_FORMAT)
        //                                    .content(insertFaqDTO)
        //                    )
        //                    .andDo(print());
        //
        //            resultActions
        //                    .andExpect(status().isOk())
        //                    .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
        //        }

        /////////////////////////////////////////////////////////////////////

        // 2. 리스트를 조회합니다.

        // given
        String url2 = "/faq/list";

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
    public void selectFaqListPagination2() throws Exception {

        // given
        String pageNo = "2";

        String url = "/faq/list?currentPage=" + pageNo;

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
    public void selectFaqListPagination3() throws Exception {

        // 1. 대량의 faq를 삽입합니다.

        //        String url = "/faq";
        //
        //        for(Integer i = 0; i < 40; i++) {
        //
        //            FaqVO faqVO = FaqVO.builder()
        //                    .title("테스트_자주하는질문_제목" + i)
        //                    .content("테스트_자주하는질문_내용" + i)
        //                    .build();
        //
        //            String insertFaqDTO = new Gson().toJson(faqVO);
        //
        //            ResultActions resultActions = mockMvc
        //                    .perform(
        //                            post(url)
        //                                    .contentType(BaseConfigController.JSON_FORMAT)
        //                                    .content(insertFaqDTO)
        //                    )
        //                    .andDo(print());
        //
        //            resultActions
        //                    .andExpect(status().isOk())
        //                    .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
        //        }

        /////////////////////////////////////////////////////////////////////

        // given
        String pageSize = "30";

        String url = "/faq/list?currentPage=1" + "&" + "pageSize=" + pageSize;

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
    public void selectFaqListPagination4() throws Exception {

        // given
        String pageNo = "2";

        String pageSize = "20";

        String url = "/faq/list?currentPage=" + pageNo + "&" + "pageSize=" + pageSize;

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
    public void selectFaq() throws Exception {

        // given
        String pageNo = "1";

        String url = "/faq?faqNo=" + pageNo;

        // when
        ResultActions resultActions = mockMvc
                .perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void insertFaq() throws Exception {

        // given
        String url = "/faq";

        String insertFaqDTO = testUtil.getDummyFaqVOToJson();

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        post(url)
                                .contentType(BaseConfigController.JSON_FORMAT)
                                .content(insertFaqDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void updateFaq() throws Exception {

        // given
        String url = "/faq";

        FaqVO faqVO = FaqVO.builder()
                .faqNo(1)
                .title(testUtil.getFaqDummyTitle())
                .content(testUtil.getFaqDummyExplain())
                .useYnEnum(FaqUseYnEnum.Y)
                .build();

        String updateFaqDTO = new Gson().toJson(faqVO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateFaqDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void updateFaqState() throws Exception {

        // given
        String url = "/faq/state";

        FaqVO faqVO = FaqVO.builder()
                .faqNo(1)
                .title(testUtil.getFaqDummyTitle())
                .content(testUtil.getFaqDummyExplain())
                .useYnEnum(FaqUseYnEnum.N)
                .build();

        String updateFaqStateDTO = new Gson().toJson(faqVO);

        // when
        ResultActions resultActions = mockMvc
                .perform(
                        put(url)
                            .contentType(BaseConfigController.JSON_FORMAT)
                            .content(updateFaqStateDTO)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void deleteFaq() throws Exception {

        // given
        String url = "/faq?faqNo=2";

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
