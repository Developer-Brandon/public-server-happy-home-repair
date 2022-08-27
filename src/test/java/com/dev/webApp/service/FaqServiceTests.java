package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.FaqVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqServiceTests {

    // TODO: faq 서비스쪽부터 로직 추가 예정

    @Autowired
    private FaqService faqService;

    @Test
    public void serviceExistTest() {
        assertNotNull(faqService);

    }

    @Test
    public void serviceRegisterTest() throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .title("새로 작성하는 글")
                .content("새로 작성하는 내용")
                .build();

        Long registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);
    }

    @Test
    public void serviceGetFaqListTest() throws Exception {

        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        faqVOList.stream().forEach(System.out::println);

        assertNotNull(faqVOList);
    }
}
