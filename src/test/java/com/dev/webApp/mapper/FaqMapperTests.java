package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.FaqVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqMapperTests {

    @Autowired
    private FaqMapper faqMapper;

    @Test
    public void getFaqList() {

        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .build();

        faqMapper.selectFaqList(selectFaqDTO)
                .forEach(faq -> System.out.println(faq.getTitle()));
    }

    @Test
    public void getFaq() {
        Long faqNo = 1L;

        FaqVO faqVO = faqMapper.selectFaq(faqNo);

        System.out.println("faqVO" + faqVO);
    }

    @Test
    public void insertFaq() {

        FaqVO faqVO = new FaqVO();

        faqVO.setTitle("삽입_테스트_제목");
        faqVO.setContent("삽입_테스트_내용");

        int insertedFaqCnt = faqMapper.insertFaq(faqVO);

        System.out.println("insertedFaqCnt" + insertedFaqCnt);

        System.out.println("insertedFaqNo" + faqVO.getFaqNo());
    }

    @Test
    public void updateFaq() {
        FaqVO faqVO = new FaqVO();
        faqVO.setFaqNo(10L);
        faqVO.setTitle("업데이트_테스트_제목");
        faqVO.setContent("업데이트_테스트_내용");

        int updatedFaqCnt = faqMapper.updateFaq(faqVO);

        System.out.println("updatedFaqCnt" + updatedFaqCnt);
    }

    @Test
    public void deleteFaq() {

        Long faqNo = 11L;

        int deletedFaqCnt = faqMapper.deleteFaq(faqNo);

        System.out.println("deletedFaqCnt" + deletedFaqCnt);
    }
}
