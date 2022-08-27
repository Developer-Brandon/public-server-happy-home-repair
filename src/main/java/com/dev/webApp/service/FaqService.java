package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.FaqVO;

import java.util.List;

public interface FaqService {

    Long registerFaq(FaqVO FaqVO) throws Exception;

    void registerFaqList(List<FaqVO> FaqVOList) throws Exception;

    void modifyFaq(FaqVO FaqVO) throws Exception;

    void modifyFaqState(FaqVO FaqVO) throws Exception;

    void removeFaq(Long FaqNo) throws Exception;

    FaqVO getFaq(FaqVO FaqVO) throws Exception;

    List<FaqVO> getFaqList(SelectFaqDTO selectFaqDTO) throws Exception;
}
