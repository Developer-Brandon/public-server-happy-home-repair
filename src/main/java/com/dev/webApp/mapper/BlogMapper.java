package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.InsertBlogPostingDTO;
import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;

import java.util.List;

public interface BlogMapper {

    List<BlogPostingVO> selectBlogPostingList();

    Integer insertBlogPosting(InsertBlogPostingDTO insertBlogPostingDTO);

    int insertBlogPostingList(InsertBlogPostingListDTO insertBlogPostingListDTO);
}
