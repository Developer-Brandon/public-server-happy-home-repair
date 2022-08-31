package com.dev.webApp.service;

import com.dev.webApp.domain.vo.BlogVO;

import java.util.List;

public interface BlogService {
    List<BlogVO> getBlogList() throws Exception;
    void setBlogList() throws Exception;
}
