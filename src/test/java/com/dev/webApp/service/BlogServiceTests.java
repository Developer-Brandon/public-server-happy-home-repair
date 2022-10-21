package com.dev.webApp.service;

import com.dev.webApp.domain.vo.RawBlogPostingVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BlogServiceTests {

    // todo: 추후 mocking 개념 도입 후 자세한 테스트 예정

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceTests.class);

    @Autowired
    private BlogService blogService;

    @Test
    public void serviceExistTest() {
        assertNotNull(blogService);
    }

    @Test
    public void serviceBlogPostingListByCrawling() throws Exception {

        List<RawBlogPostingVO> rawBlogPostingVOList = blogService.getRawBlogPostingListByCrawling(1000);

        rawBlogPostingVOList.forEach(e -> {

            //
            logger.info("rawBlogPostingVOList.forEach e.getTitle: {}", e.getTitle());
        });
    }
}
