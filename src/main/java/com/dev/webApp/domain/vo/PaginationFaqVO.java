package com.dev.webApp.domain.vo;

import com.dev.webApp.util.page.PageHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFaqVO {

    PageHandler pageHandler;

    List<FaqVO> faqVOList;
}
