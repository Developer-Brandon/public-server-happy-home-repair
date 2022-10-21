package com.dev.webApp.mapper.repairApply;

import com.dev.webApp.domain.dto.SelectRepairLocationDTO;
import com.dev.webApp.domain.dto.SelectRepairStateDTO;
import com.dev.webApp.domain.dto.SelectRepairTypeDTO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;
import com.dev.webApp.mapper.RepairMapper;
import com.dev.webApp.persistenceConfiguration.DBConnectionTest;
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

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RepairMapperTests {

    private static Logger logger = LoggerFactory.getLogger(DBConnectionTest.class);

    @Autowired
    private RepairMapper repairMapper;

    @Test
    public void getRepairTypeList() {

        // 1. 리스트 조회 테스트

        SelectRepairTypeDTO selectRepairTypeDTO = SelectRepairTypeDTO.builder()
                .itemCnt(5)
                .build();

        List<RepairTypeVO> repairTypeVOList = repairMapper.selectRepairTypeList(selectRepairTypeDTO);

        assertThat(repairTypeVOList, is(notNullValue()));
        assertThat(repairTypeVOList.size(), is(greaterThan(1)));
    }

    @Test
    public void getRepairType() throws Exception {

        // 1. 여기서 삽입테스트는 먼저 하지 않아도 되는 것이, repairType은 반드시 raw data가 존재해야하는 data입니다.

        //////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairTypeVO repairTypeVO = repairMapper
                .selectRepairType(1);

        //
        assertThat(repairTypeVO, is(notNullValue()));
        assertThat(repairTypeVO.getTitle(), is("PAINT"));
        assertThat(repairTypeVO.getExplanation(), is("친환경 페인트"));
    }

    @Test
    public void getRepairLocationList() {

        // 1. 리스트 조회 테스트

        SelectRepairLocationDTO selectRepairLocationDTO = SelectRepairLocationDTO.builder()
                .itemCnt(5)
                .build();

        List<RepairLocationVO> repairLocationVOList = repairMapper.selectRepairLocationList(selectRepairLocationDTO);

        assertThat(repairLocationVOList, is(notNullValue()));
        assertThat(repairLocationVOList.size(), is(greaterThan(1)));
    }

    @Test
    public void getRepairLocation() throws Exception {

        // 1. 여기서 삽입테스트는 먼저 하지 않아도 되는 것이, repairLocation은 반드시 raw data가 존재해야하는 data입니다.

        //////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairLocationVO repairLocationVO = repairMapper
                .selectRepairLocation(1);

        //
        assertThat(repairLocationVO, is(notNullValue()));
        assertThat(repairLocationVO.getName(), is("SEOUL"));
    }

    @Test
    public void getRepairStateList() {

        // 1. 리스트 조회 테스트

        SelectRepairStateDTO selectRepairStateDTO = SelectRepairStateDTO.builder()
                .itemCnt(5)
                .build();

        List<RepairStateVO> selectRepairLocationList = repairMapper.selectRepairStateList(selectRepairStateDTO);

        assertThat(selectRepairLocationList, is(notNullValue()));
        assertThat(selectRepairLocationList.size(), is(greaterThan(1)));
    }

    @Test
    public void getRepairState() throws Exception {

        // 1. 여기서 삽입테스트는 먼저 하지 않아도 되는 것이, repairState은 반드시 raw data가 존재해야하는 data입니다.

        //////////////////////////////////////////////////////////

        // 2. 조회 테스트
        RepairStateVO repairStateVO = repairMapper
                .selectRepairState(1);

        //
        assertThat(repairStateVO, is(notNullValue()));
        assertThat(repairStateVO.getName(), is("APPLY"));
        assertThat(repairStateVO.getExplanation(), is("접수"));
    }
}
