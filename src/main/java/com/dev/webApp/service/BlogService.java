package com.dev.webApp.service;

import com.dev.webApp.domain.vo.BlogPostingVO;

import java.util.List;

public interface BlogService {
    List<BlogPostingVO> getBlogList() throws Exception;
    void setBlogList() throws Exception;
    void setDifferentBlogList() throws Exception;
}
