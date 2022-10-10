package com.dev.webApp.service;

import com.dev.webApp.domain.vo.RawBlogPostingVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BlogServiceTests {

    @Autowired
    private BlogService blogService;

    @Test
    public void serviceExistTest() {
        assertNotNull(blogService);

    }

    @Test
    public void serviceBlogPostingListByCrawling() throws Exception {

        List<RawBlogPostingVO> rawBlogPostingVOList = blogService.getRawBlogPostingListByCrawling(1000);
    }
}
