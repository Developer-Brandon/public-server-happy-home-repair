package com.dev.webApp.mapper.faq;

import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.mapper.FaqMapper;
import com.dev.webApp.util.FaqUseYnEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FaqMapperTests {

    // greaterThan, lessThan
    // https://www.baeldung.com/hamcrest-number-matchers

    @Autowired
    private FaqMapper faqMapper;

    @Before
    public void init() {
    }

    @Test
    public void getFaqList() {

        SelectFaqPaginationDTO selectFaqPaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(1)
                .offset(1) // service단에서 처리해주는 처리를 대신...
                .build();

        faqMapper.selectFaqPaginationList(selectFaqPaginationDTO)
                .forEach(faq -> System.out.println(faq.getTitle()));
    }

    @Test
    public void getFaq() {

        // 1. 조회 전 삽입

        FaqVO faqVO = new FaqVO();
        faqVO.setTitle("삽입_테스트_제목");
        faqVO.setContent("삽입_테스트_내용");

        Boolean insertedOrNot = faqMapper.insertFaq(faqVO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(faqVO.getFaqNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 삽입된 데이터로 조회

        Integer faqNo = faqVO.getFaqNo();

        FaqVO faqVO2 = faqMapper.selectFaq(faqNo);

        assertThat(faqVO2, is(notNullValue()));
    }

    @Test
    public void insertFaq() {

        // 1. 삽입 테스트

        FaqVO faqVO = new FaqVO();
        faqVO.setTitle("삽입_테스트_제목");
        faqVO.setContent("삽입_테스트_내용");

        Boolean insertedOrNot = faqMapper.insertFaq(faqVO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(faqVO.getFaqNo(), is(greaterThan(1)));
    }

    @Test
    public void updateFaq() {

        // 1. 조회 전 삽입

        FaqVO faqVO = new FaqVO();
        faqVO.setTitle("삽입_테스트_제목");
        faqVO.setContent("삽입_테스트_내용");

        Boolean insertedOrNot = faqMapper.insertFaq(faqVO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(faqVO.getFaqNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 삽입된 데이터로 조회

        Integer faqNo = faqVO.getFaqNo();

        FaqVO faqVO2 = faqMapper.selectFaq(faqNo);

        assertThat(faqVO2, is(notNullValue()));

        ///////////////////////////////////////////////////////////

        // 3. 다시 조회된 데이터로 업데이트

        FaqVO faqVO3 = new FaqVO();
        faqVO3.setFaqNo(faqNo);
        faqVO3.setTitle("업데이트_테스트_제목");
        faqVO3.setContent("업데이트_테스트_내용");
        faqVO3.setUseYnEnum(FaqUseYnEnum.Y);

        int updatedFaqCnt = faqMapper.updateFaq(faqVO3);

        assertThat(updatedFaqCnt, is(1));

        ///////////////////////////////////////////////////////////

        // 4. 업데이트 여부 검증

        FaqVO faqVO4 = faqMapper.selectFaq(faqNo);

        assertThat(faqVO4, is(notNullValue()));
        assertThat(faqVO4.getTitle(), is("업데이트_테스트_제목"));
        assertThat(faqVO4.getContent(), is("업데이트_테스트_내용"));
    }

    @Test
    public void deleteFaq() {

        // 1. 조회 전 삽입

        FaqVO faqVO = new FaqVO();
        faqVO.setTitle("삽입_테스트_제목");
        faqVO.setContent("삽입_테스트_내용");

        Boolean insertedOrNot = faqMapper.insertFaq(faqVO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(faqVO.getFaqNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 삽입된 데이터로 조회

        Integer faqNo = faqVO.getFaqNo();

        FaqVO faqVO2 = faqMapper.selectFaq(faqNo);

        assertThat(faqVO2, is(notNullValue()));

        ///////////////////////////////////////////////////////////

        // 3. 조회된 데이터로 삭제

        Boolean deletedOrNot = faqMapper.deleteFaq(faqNo) == 1;

        assertThat(deletedOrNot, is(true));
    }
}
