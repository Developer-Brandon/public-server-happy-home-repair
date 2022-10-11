package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.PaginationFaqVO;

public interface FaqService {

    PaginationFaqVO getFaqPaginationList(SelectFaqPaginationDTO selectFaqPaginationDTO) throws Exception;

    FaqVO getFaq(FaqVO FaqVO) throws Exception;

    Integer registerFaq(FaqVO FaqVO) throws Exception;

    void modifyFaq(FaqVO FaqVO) throws Exception;

    void modifyFaqState(FaqVO FaqVO) throws Exception;

    void removeFaq(Integer FaqNo) throws Exception;

    Integer getTotalCnt();
}
