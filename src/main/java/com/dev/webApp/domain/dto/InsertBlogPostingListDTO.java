package com.dev.webApp.domain.dto;

import com.dev.webApp.domain.vo.RawBlogPostingVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class InsertBlogPostingListDTO {
    List<RawBlogPostingVO> insertBlogPostingDTOList;
}
