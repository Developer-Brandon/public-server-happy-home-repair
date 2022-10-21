package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.util.FaqUseYnEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
    public void selectWholeCount() throws Exception {

        for(Integer i = 0; i < 10; i++) {

            FaqVO faqVO = FaqVO.builder()
                    .title("새로_작성하는_글")
                    .content("새로_작성하는_내용")
                    .build();

            Integer registerSuccessorNot = faqService.registerFaq(faqVO);

            assertNotNull(registerSuccessorNot);
        }

        ////////////////////////////////////////////////

        Integer totalCount = faqService.getTotalCnt();

        assertThat(totalCount, is(greaterThan(1)));
    }

    @Test
    public void serviceRegisterTest() throws Exception {

        // 1. 삽입 테스트

        FaqVO faqVO = FaqVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);
    }

    @Test
    public void serviceUpdateTests() throws Exception {

        // 1. 삽입 테스트

        FaqVO faqVO = FaqVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        FaqVO faqVO2 = FaqVO.builder()
                .faqNo(faqVO.getFaqNo())
                .title("새로_수정하는_글")
                .content("새로_수정하는_내용")
                .useYnEnum(FaqUseYnEnum.Y)
                .build();

       faqService.modifyFaq(faqVO2);
    }

    @Test
    public void serviceUpdateStateTests() throws Exception {

        // 1. 삽입 테스트

        FaqVO faqVO = FaqVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        FaqVO faqVO2 = FaqVO.builder()
                .faqNo(faqVO.getFaqNo())
                .title("새로_수정하는_글")
                .content("새로_수정하는_내용")
                .useYnEnum(FaqUseYnEnum.N)
                .build();

        faqService.modifyFaqState(faqVO2);
    }

    @Test
    public void serviceRemoveTests() throws Exception {

        // 1. 삽입 테스트

        FaqVO faqVO = FaqVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 수정 테스트

        faqService.removeFaq(faqVO.getFaqNo());
    }

    @Test
    public void serviceGetFaqListTest() throws Exception {

        // 1. 삽입 테스트

        FaqVO faqVO = FaqVO.builder()
                .title("새로_작성하는_글")
                .content("새로_작성하는_내용")
                .build();

        Integer registerSuccessorNot = faqService.registerFaq(faqVO);

        assertNotNull(registerSuccessorNot);

        ////////////////////////////////////////////////////////////

        // 2. 조회 테스트

        SelectFaqPaginationDTO selectFaqDTO = SelectFaqPaginationDTO.builder()
                .currentPage(1)
                .build();

        List<FaqVO> faqVOList = faqService
                .getFaqPaginationList(selectFaqDTO)
                .getFaqVOList();

        faqVOList.forEach(System.out::println);

        faqVOList.forEach(e -> {
            logger.info("faqVOList.forEach: {}", e);
        });

        assertNotNull(faqVOList);
    }
}
