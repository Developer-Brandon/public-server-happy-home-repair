package com.dev.webApp.mapper;

import com.dev.webApp.domain.vo.FaqVO;

import java.util.List;

public interface FaqMapper {

    List<FaqVO> selectFaqList();

    List<FaqVO> selectFaqListBySize(Integer size);

    FaqVO selectFaq(Long faqNo);

    int insertFaq(FaqVO faqVO);

    int insertFaqList(List<FaqVO> faqVOList);

    int updateFaq(FaqVO faqNo);

    int updateFaqState(FaqVO faqVO);

    int deleteFaq(Long faqNo);
}
