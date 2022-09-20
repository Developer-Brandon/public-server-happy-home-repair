package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.InsertBlogPostingDTO;
import com.dev.webApp.domain.dto.InsertBlogPostingListDTO;
import com.dev.webApp.domain.dto.SelectBlogPostingPaginationDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;

import java.util.List;

public interface BlogMapper {

    int getTotalCnt();

    List<BlogPostingVO> selectAllBlogPostingList();

    List<BlogPostingVO> selectBlogPostingList(SelectBlogPostingPaginationDTO selectRepairApplyPaginationDTO);

    Integer insertBlogPosting(InsertBlogPostingDTO insertBlogPostingDTO);

    int insertBlogPostingList(InsertBlogPostingListDTO insertBlogPostingListDTO);
}
