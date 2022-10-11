package com.dev.webApp.mapper.repairApply;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.mapper.RepairMapper;
import com.dev.webApp.service.BlogServiceTests;
import com.dev.webApp.util.AgreeOrNotEnum;
import com.dev.webApp.util.UserTypeEnum;
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
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RepairApplyMapperTests {

    private static final Logger logger = LoggerFactory.getLogger(BlogServiceTests.class);

    @Autowired
    private RepairMapper repairMapper;

    @Test
    public void insertRepairApply() {

        // 1. 삽입 테스트
        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터")
                .build();

        Boolean insertedOrNot = repairMapper.insertRepairApply(insertRepairApplyDTO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(insertRepairApplyDTO.getInsertedRepairApplyNo(), is(greaterThan(1)));
    }

    @Test
    public void getRepairApply() {

        // 1. 삽입 테스트

        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터")
                .build();

        Boolean insertedOrNot = repairMapper.insertRepairApply(insertRepairApplyDTO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(insertRepairApplyDTO.getInsertedRepairApplyNo(), is(greaterThan(1)));

        //////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairApplyVO repairApplyVO = repairMapper.selectRepairApply(insertRepairApplyDTO.getInsertedRepairApplyNo());

        //
        assertThat(repairApplyVO, is(notNullValue()));
        assertThat(repairApplyVO.getPhoneNumber(), is("01088884444"));
        assertThat(repairApplyVO.getExplanation(), is("테스트_데이터"));
    }


    @Test
    public void getRepairApplyList() {

        // 1. 조회 전 삽입
        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터")
                .build();

        Boolean insertedOrNot = repairMapper.insertRepairApply(insertRepairApplyDTO) == 1;

        logger.info("insertRepairApplyDTO.getInsertedRepairApplyNo(): {}", insertRepairApplyDTO.getInsertedRepairApplyNo());

        assertThat(insertedOrNot, is(true));
        assertThat(insertRepairApplyDTO.getInsertedRepairApplyNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 리스트 조회 테스트

        SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO = SelectRepairApplyPaginationDTO.builder()
                .currentPage(1) // pk가 뒤로 밀렷을때를 대비해서...
                .offset(0) // service 단에서 처리해주어야할 처리, 하지만 transaction 처리로 인해 table이 텅텅 비었을 가능성이 있으므로, 0부터 count 하는 것으로 처리
                .build();

        List<RepairApplyVO> selectRepairTypeList = repairMapper.selectRepairApplyList(selectRepairApplyPaginationDTO);

        assertThat(selectRepairTypeList, is(notNullValue()));
        // service 단에서 처리해주어야할 처리, 하지만 transaction 처리로 인해 table이 텅텅 비었을 가능성이 있으므로, '1'로 처리
        assertThat(selectRepairTypeList.size(), is(1));
    }

    @Test
    public void updateRepairApply() {

        // 1. 조회 전 삽입
        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터")
                .build();

        Boolean insertedOrNot = repairMapper.insertRepairApply(insertRepairApplyDTO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(insertRepairApplyDTO.getInsertedRepairApplyNo(), is(greaterThan(1)));

        ///////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairApplyVO repairApplyVO = repairMapper.selectRepairApply(insertRepairApplyDTO.getInsertedRepairApplyNo());

        //
        assertThat(repairApplyVO, is(notNullValue()));
        assertThat(repairApplyVO.getPhoneNumber(), is("01088884444"));
        assertThat(repairApplyVO.getExplanation(), is("테스트_데이터"));

        ///////////////////////////////////////////////////////////

        // 3. 다시 조회된 데이터로 업데이트
        UpdateRepairApplyDTO updateRepairApplyDTO = UpdateRepairApplyDTO.builder()
                .repairApplyNo(insertRepairApplyDTO.getInsertedRepairApplyNo())
                .repairTypeNo(2)
                .repairLocationNo(2)
                .repairStateNo(2)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01099994444")
                .explanation("업데이트_테스트_내용")
                .build();

        int updatedRepairApplyCnt = repairMapper.updateRepairApply(updateRepairApplyDTO);

        assertThat(updatedRepairApplyCnt, is(1));

        ///////////////////////////////////////////////////////////

        // 4. 업데이트 여부 검증

        RepairApplyVO repairApplyVO2 = repairMapper.selectRepairApply(insertRepairApplyDTO.getInsertedRepairApplyNo());

        //
        assertThat(repairApplyVO2, is(notNullValue()));
        assertThat(repairApplyVO2.getPhoneNumber(), is("01099994444"));
        assertThat(repairApplyVO2.getExplanation(), is("업데이트_테스트_내용"));
    }

    @Test
    public void deleteFaq() {

        // 1. 삭제 전 삽입
        InsertRepairApplyDTO insertRepairApplyDTO = InsertRepairApplyDTO.builder()
                .repairTypeNo(1)
                .repairLocationNo(1)
                .repairStateNo(1)
                .userTypeEnum(UserTypeEnum.CLIENT)
                .phoneNumber("01088884444")
                .agreeOrNotEnum(AgreeOrNotEnum.Y)
                .explanation("테스트_데이터")
                .build();

        Boolean insertedOrNot = repairMapper.insertRepairApply(insertRepairApplyDTO) == 1;

        assertThat(insertedOrNot, is(true));
        assertThat(insertRepairApplyDTO.getInsertedRepairApplyNo(), is(greaterThan(0)));

        //////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairApplyVO repairApplyVO = repairMapper.selectRepairApply(insertRepairApplyDTO.getInsertedRepairApplyNo());

        //
        assertThat(repairApplyVO, is(notNullValue()));
        assertThat(repairApplyVO.getPhoneNumber(), is("01088884444"));
        assertThat(repairApplyVO.getExplanation(), is("테스트_데이터"));

        ///////////////////////////////////////////////////////////

        // 3. 조회된 데이터로 삭제

        Boolean deletedOrNot = repairMapper.deleteRepairApply(insertRepairApplyDTO.getInsertedRepairApplyNo()) == 1;

        assertThat(deletedOrNot, is(true));
    }
}
