package com.dev.webApp.mapper;

import com.dev.webApp.domain.vo.BlogVO;

import java.util.List;

public interface BlogMapper {

    List<BlogVO> selectBlogPostingList();


}
