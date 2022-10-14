package com.dev.webApp.controller.blog;

import com.dev.webApp.config.controller.BaseConfigController;
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

import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.bytebuddy.matcher.ElementMatchers.isArray;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class BlogController2Test extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectBlogListByDB() throws Exception {

        // 데이터의 유무에 관계 없이, 블로그 리스트를 db에서 잘 불러오는지 확인

        // given
        String url = "/blog/list";

        // when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT));
    }

    @Test
    public void selectBlogListByDBWhenDataExists() throws Exception {

        // 데이터가 있다면, 블로그 리스트를 db에서 잘 불러오는지 확인

        // 1. data 통째로 삽입

        // given
        String url = "/blog/list/bulk";

        // when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));


        ////////////////////////////////////////////////////////////////

        // 2. 확인

        // given
        String url2 = "/blog/list";

        // when
        ResultActions resultActions2 = mockMvc.perform(get(url2))
                .andDo(print());

        // then
        resultActions2
                .andExpect(status().is(200))
                .andExpect(content().contentType(BaseConfigController.JSON_FORMAT))
                .andExpect(jsonPath("$.[0].postingNo").exists())
                .andExpect(jsonPath("$.[0].postingNo").isNumber())
                .andExpect(jsonPath("$.[0].title").exists())
                .andExpect(jsonPath("$.[0].title").isString());
    }

    @Test
    public void insertBulkDataToDB() throws Exception {

        //
        mockMvc.perform(
                get("/blog/list/bulk")
        )
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateDifferentBlogListToDB() throws Exception {

        //
        mockMvc.perform(
                get("/blog/list/diff-bulk")
        )
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();
    }
}
