package com.dev.webApp.mapper.blog;

import com.dev.webApp.domain.dto.InsertBlogPostingDTO;
import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.RawBlogPostingVO;
import com.dev.webApp.mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BlogMapperTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void getBlogMapperList() {

        // 1. 조회 테스트

        List<BlogPostingVO> blogPostingVOList = blogMapper.selectAllBlogPostingList();

        assertThat(blogPostingVOList, is(notNullValue()));
        assertThat(blogPostingVOList.size(), is(greaterThan(1)));
    }


    @Test
    public void insertBlog() {

        // 1. 삽입 테스트

        InsertBlogPostingDTO insertBlogPostingDTO = InsertBlogPostingDTO.builder()
                .postingTitle("제목_테스트")
                .postingTypeNo(1)
                .postingImageSrc("이미지경로_테스트")
                .postingRegDt("2022-09-07 12:48:52")
                .build();

        Boolean insertedOrNot = blogMapper.insertBlogPosting(insertBlogPostingDTO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(insertBlogPostingDTO.getInsertedPostingNo(), is(greaterThan(1)));
    }

    @Test
    public void insertBlogList() {

        // 1. 리스트 삽입 테스트

        RawBlogPostingVO rawBlogPostingVO = RawBlogPostingVO.builder()
                .title("제목_테스트")
                .imgSrc("이미지경로_테스트")
                .postingRegDt("2022-09-07 12:48:52")
                .build();

        RawBlogPostingVO rawBlogPostingVO2 = RawBlogPostingVO.builder()
                .title("제목_테스트2")
                .imgSrc("이미지경로_테스트2")
                .postingRegDt("2022-09-07 12:48:52")
                .build();

        RawBlogPostingVO rawBlogPostingVO3 = RawBlogPostingVO.builder()
                .title("제목_테스트3")
                .imgSrc("이미지경로_테스트3")
                .postingRegDt("2022-09-07 12:48:52")
                .build();

        //
        List<RawBlogPostingVO> insertBlogPostingDTOList = new ArrayList<>();
        insertBlogPostingDTOList.add(rawBlogPostingVO);
        insertBlogPostingDTOList.add(rawBlogPostingVO2);
        insertBlogPostingDTOList.add(rawBlogPostingVO3);

        InsertBlogPostingListDTO insertBlogPostingListDTO = InsertBlogPostingListDTO.builder()
                .insertBlogPostingDTOList(insertBlogPostingDTOList)
                .build();

        Boolean insertedOrNot = blogMapper.insertBlogPostingList(insertBlogPostingListDTO) == 3;

        assertThat(insertedOrNot, is(true));
    }

}
