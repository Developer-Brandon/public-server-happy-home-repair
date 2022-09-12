package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.mapper.FaqMapper;
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
    public Long registerFaq(FaqVO FaqVO) throws Exception {

        if(faqMapper.insertFaq(FaqVO) != 1) {
            throw new Exception();
        }

        // 반환값으로 삽입된 게시물의 번호를 반환합니다.
        return FaqVO.getFaqNo();
    }

    @Override
    public void registerFaqList(List<FaqVO> FaqVOList) throws Exception {

        // 게시물 리스트 정보를 통째로 등록하는 메소드입니다.
        if(faqMapper.insertFaqList(FaqVOList) == 0) {
            throw new Exception();
        }
    }

    @Override
    public FaqVO getFaq(FaqVO FaqVO) throws Exception {

        FaqVO selectedFaqVO = faqMapper.selectFaq(FaqVO.getFaqNo());

        if(selectedFaqVO.getFaqNo() == 0L) {
            throw new Exception();
        }

        return selectedFaqVO;
    }

    @Override
    public List<FaqVO> getFaqList(SelectFaqDTO selectFaqDTO) throws Exception{

        return faqMapper.selectFaqList(selectFaqDTO);
    }

    @Override
    public void modifyFaq(FaqVO FaqVO) throws Exception {

        if(faqMapper.updateFaq(FaqVO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void modifyFaqState(FaqVO FaqVO) throws Exception {

        if(faqMapper.updateFaqState(FaqVO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeFaq(Long FaqNo) throws Exception {

        if(faqMapper.deleteFaq(FaqNo) != 1) {
            throw new Exception();
        }
    }
}
