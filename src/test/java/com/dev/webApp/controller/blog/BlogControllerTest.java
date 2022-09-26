package com.dev.webApp.controller.blog;

import com.dev.webApp.service.BlogService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) // test for using junit4
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration // Test for controller
public class BlogControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private BlogService blogService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void selectBlogListByDB() throws Exception {

        //
        mockMvc.perform(
                    get("/blog/list")
                )
                .andExpect(status().is(200))
                .andDo(print())
                .andReturn();
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
