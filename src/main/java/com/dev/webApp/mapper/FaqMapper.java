package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;

import java.util.List;

public interface FaqMapper {

    List<FaqVO> selectFaqPaginationList(SelectFaqPaginationDTO selectFaqPaginationDTO);

    FaqVO selectFaq(Integer faqNo);

    int insertFaq(FaqVO faqVO);

    int updateFaq(FaqVO faqNo);

    int updateFaqState(FaqVO faqVO);

    int deleteFaq(Integer faqNo);

    int getTotalCnt();
}
