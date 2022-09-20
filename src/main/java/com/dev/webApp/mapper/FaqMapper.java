package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.NoticeVO;

import java.util.List;

public interface FaqMapper {

    List<FaqVO> selectFaqList(SelectFaqDTO selectFaqDTO);

    List<FaqVO> selectFaqPaginationList(SelectFaqPaginationDTO selectFaqPaginationDTO);

    FaqVO selectFaq(Long faqNo);

    int insertFaq(FaqVO faqVO);

    int insertFaqList(List<FaqVO> faqVOList);

    int updateFaq(FaqVO faqNo);

    int updateFaqState(FaqVO faqVO);

    int deleteFaq(Long faqNo);

    int getTotalCnt();
}
