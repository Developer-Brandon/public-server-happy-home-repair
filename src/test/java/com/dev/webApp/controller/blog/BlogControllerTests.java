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
public class BlogControllerTests extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectBlogListByDB() throws Exception {

        // 데이터의 유무에 관계 없이, 블로그 리스트를 db에서 잘 불러오는지 확인합니다.

        // given
        String url = "/blog/list";

        // when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andDo(print());

        // then
        resultActions
                .andExpect(status().is(200));
    }

    @Test
    public void selectBlogListByDBWhenDataExists() throws Exception {

        // 데이터를 아예 삽입하여, 블로그 리스트를 db에서 잘 불러오는지 확인합니다.

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
                .andExpect(jsonPath("$.[0].title").isString())
                .andExpect(jsonPath("$.[0].imgSrc").exists())
                .andExpect(jsonPath("$.[0].imgSrc").isString())
                .andExpect(jsonPath("$.[0].postingRegDt").exists())
                .andExpect(jsonPath("$.[0].postingRegDt").isString())
                .andExpect(jsonPath("$.[0].regDt").exists())
                .andExpect(jsonPath("$.[0].regDt").isString());
    }

    @Test
    public void updateDifferentBlogListToDB() throws Exception {

        // 데이터가 있다면, 다른 리트만큼만 띄어서 db에서 잘 삽입되고 있는지 확인합니다.

        // 1. 다른데이터가 있는지 비교하고 확인

        // 2. 다른 데이터의 리스트만큼 만업데이트 되는지 확인

        // 위의 테스트는 컨트롤러에서는 자세하게 테스트하기 어려운 주제의 테스트입니다.
        // 하여 이곳에서는 개략적인 api 성공여부만을

        mockMvc.perform(
                get("/blog/list/diff-bulk")
        )
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();
    }
}
