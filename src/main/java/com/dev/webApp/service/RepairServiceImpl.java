package com.dev.webApp.service;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.mapper.RepairMapper;
import com.dev.webApp.util.page.PageHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RepairServiceImpl implements RepairService{

    private static Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);

    private final RepairMapper repairMapper;

    @Override
    public List<RepairTypeVO> getRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO) throws Exception {

        List<RepairTypeVO> repairTypeVOList = repairMapper.selectRepairTypeList(selectRepairTypeDTO);

        if(repairTypeVOList.size() == 0) {
            throw new Exception("조회하는 과정에서 문제가 발생하였습니다. 반드시 존재해야하는 데이터입니다.");
        }

        return repairTypeVOList;
    }

    @Override
    public RepairTypeVO getRepairType(Integer repairTypeNo) throws Exception {

        RepairTypeVO repairTypeVO = repairMapper
                .selectRepairType(repairTypeNo)
                .orElseThrow(Exception::new);

        return repairTypeVO;
    }

    @Override
    public List<RepairLocationVO> getRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO) throws Exception {

        List<RepairLocationVO> repairLocationVOList = repairMapper.selectRepairLocationList(selectRepairLocationDTO);

        if(repairLocationVOList.size() == 0) {
            throw new Exception("조회하는 과정에서 문제가 발생하였습니다. 반드시 존재해야하는 데이터입니다.");
        }

        return repairLocationVOList;
    }

    @Override
    public RepairLocationVO getRepairLocation(Integer repairLocationNo) throws Exception {

        RepairLocationVO repairLocationVO = repairMapper
                .selectRepairLocation(repairLocationNo)
                .orElseThrow(Exception::new);

        return repairLocationVO;
    }

    @Override
    public List<RepairStateVO> getRepairStateList(SelectRepairStateDTO selectRepairStateDTO) throws Exception {

        List<RepairStateVO> repairStateVOS = repairMapper.selectRepairStateList(selectRepairStateDTO);

        if(repairStateVOS.size() == 0) {
            throw new Exception("조회하는 과정에서 문제가 발생하였습니다. 반드시 존재해야하는 데이터입니다.");
        }

        return repairStateVOS;
    }

    @Override
    public RepairStateVO getRepairState(Integer repairStateNo) throws Exception {

        RepairStateVO repairStateVO = repairMapper
                .selectRepairState(repairStateNo)
                .orElseThrow(Exception::new);

        return repairStateVO;
    }

    @Override
    public PaginationRepairApplyVO getRepairApplyList(SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO) throws Exception {

        int totalCnt = repairMapper.getTotalCnt();

        PageHandler pageHandler = new PageHandler(totalCnt, selectRepairApplyPaginationDTO.getCurrentPage());

        int offset = selectRepairApplyPaginationDTO.getCurrentPage() - 1;

        selectRepairApplyPaginationDTO.setOffset(offset * selectRepairApplyPaginationDTO.getPageSize());

        selectRepairApplyPaginationDTO.setPageSize(selectRepairApplyPaginationDTO.getPageSize());

        List<RepairApplyVO> repairApplyVOList = repairMapper.selectRepairApplyList(selectRepairApplyPaginationDTO);

        return PaginationRepairApplyVO.builder()
                .pageHandler(pageHandler)
                .repairApplyVOList(repairApplyVOList)
                .build();
    }

    @Override
    public RepairApplyVO getRepairApply(Integer repairApplyNo) throws Exception {

        RepairApplyVO repairApplyVO = repairMapper
                .selectRepairApply(repairApplyNo)
                .orElseThrow(Exception::new);

        return repairApplyVO;
    }

    @Override
    public Integer registerRepairApply(InsertRepairApplyDTO insertRepairApplyDTO) throws Exception {

        if(repairMapper.insertRepairApply(insertRepairApplyDTO) != 1) {
            throw new Exception();
        }

        return insertRepairApplyDTO.getInsertedRepairApplyNo();
    }

    @Override
    public void modifyRepairApply(UpdateRepairApplyDTO updateRepairApplyDTO) throws Exception {

        if(repairMapper.updateRepairApply(updateRepairApplyDTO) != 1) {
            throw new Exception();
        }
    }

    @Override
    public void removeRepairApply(Integer repairApplyNo) throws Exception {

        if(repairMapper.deleteRepairApply(repairApplyNo) != 1) {
            throw new Exception();
        }
    }
}
