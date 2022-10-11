package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(FaqServiceTests.class);

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

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);
    }

    @Test
    public void serviceGetFaqListTest() throws Exception {

        SelectFaqPaginationDTO selectFaqDTO = SelectFaqPaginationDTO.builder()
                .currentPage(10)
                .build();

        List<FaqVO> faqVOList = faqService
                .getFaqPaginationList(selectFaqDTO)
                .getFaqVOList();

        faqVOList.stream().forEach(System.out::println);

        faqVOList.forEach(e -> {
            logger.info("faqVOList.forEach: {}", e);
        });

        assertNotNull(faqVOList);
    }
}
