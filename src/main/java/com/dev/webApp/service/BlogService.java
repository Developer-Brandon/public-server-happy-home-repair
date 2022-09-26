package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectBlogPostingPaginationDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.PaginationBlogPostingVO;

import java.util.List;

public interface BlogService {

    List<BlogPostingVO> getAllBlogList();
    PaginationBlogPostingVO getBlogList(SelectBlogPostingPaginationDTO selectBlogPostingPaginationDTO) throws Exception;
    void setBlogList() throws Exception;
    void setOnlyDifferentBlogList() throws Exception;
}
