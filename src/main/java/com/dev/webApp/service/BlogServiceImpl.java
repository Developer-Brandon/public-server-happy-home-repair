package com.dev.webApp.service;

import com.dev.webApp.domain.vo.BlogVO;
import com.dev.webApp.mapper.BlogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogMapper blogMapper;

    @Override
    public List<BlogVO> getBlogList() throws Exception {
        return blogMapper.selectBlogPostingList();
    }

    @Override
    public void setBlogList() throws Exception {

        // todo: 여기서부터 다시...
    }
}
