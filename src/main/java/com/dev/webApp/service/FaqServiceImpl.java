package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.PaginationFaqVO;
import com.dev.webApp.mapper.FaqMapper;
import com.dev.webApp.util.page.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FaqServiceImpl implements FaqService{

    private final FaqMapper faqMapper;

    @Override
    public PaginationFaqVO getFaqPaginationList(SelectFaqPaginationDTO selectFaqPaginationDTO) throws Exception {

        int totalCnt = faqMapper.getTotalCnt();

        PageHandler pageHandler = new PageHandler(totalCnt, selectFaqPaginationDTO.getCurrentPage());

        Integer offset = selectFaqPaginationDTO.getCurrentPage() - 1;

        selectFaqPaginationDTO.setOffset(offset * selectFaqPaginationDTO.getPageSize());

        selectFaqPaginationDTO.setPageSize(selectFaqPaginationDTO.getPageSize());

        List<FaqVO> faqPaginationList = faqMapper.selectFaqPaginationList(selectFaqPaginationDTO);

        return PaginationFaqVO.builder()
                .faqVOList(faqPaginationList)
                .pageHandler(pageHandler)
                .build();
    }

    @Override
    public FaqVO getFaq(FaqVO FaqVO) throws Exception {

        FaqVO selectedFaqVO = faqMapper.selectFaq(FaqVO.getFaqNo());

        if(selectedFaqVO.getFaqNo() == 0L) {
            throw new Exception("게시물을 조회하는 과정에서 문제가 발생하였습니다.");
        }

        return selectedFaqVO;
    }

    @Override
    public Integer registerFaq(FaqVO FaqVO) throws Exception {

        if(faqMapper.insertFaq(FaqVO) != 1) {
            throw new Exception("게시물을 삽입하는 과정에서 문제가 발생하였습니다.");
        }

        // 반환값으로 삽입된 게시물의 번호를 반환합니다.
        return FaqVO.getFaqNo();
    }


    @Override
    public void modifyFaq(FaqVO FaqVO) throws Exception {

        if(faqMapper.updateFaq(FaqVO) != 1) {
            throw new Exception("게시물을 수정하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public void modifyFaqState(FaqVO FaqVO) throws Exception {

        if(faqMapper.updateFaqState(FaqVO) != 1) {
            throw new Exception("게시물의 상태를 수정하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public void removeFaq(Integer FaqNo) throws Exception {

        if(faqMapper.deleteFaq(FaqNo) != 1) {
            throw new Exception("게시물을 삭제하는 과정에서 문제가 발생하였습니다.");
        }
    }

    @Override
    public Integer getTotalCnt() {
        return faqMapper.getTotalCnt();
    }
}
