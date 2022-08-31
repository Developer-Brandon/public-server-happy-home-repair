package com.dev.webApp.service;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.mapper.FaqMapper;
import com.dev.webApp.mapper.RepairMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.annotation.ExceptionProxy;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RepairServiceImpl implements RepairService{

    private final RepairMapper repairMapper;

    @Override
    public List<RepairTypeVO> getRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO) {
        return repairMapper.selectRepairTypeList(selectRepairTypeDTO);
    }

    @Override
    public RepairTypeVO getRepairType(Integer repairTypeNo) {
        return repairMapper.selectRepairType(repairTypeNo);
    }

    @Override
    public List<RepairLocationVO> getRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO) {
        return repairMapper.selectRepairLocationList(selectRepairLocationDTO);
    }

    @Override
    public RepairLocationVO getRepairLocation(Integer repairLocationNo) {
        return repairMapper.selectRepairLocation(repairLocationNo);
    }

    @Override
    public List<RepairStateVO> getRepairStateList(SelectRepairStateDTO selectRepairStateDTO) {
        return repairMapper.selectRepairStateList(selectRepairStateDTO);
    }

    @Override
    public RepairStateVO getRepairState(Integer repairStateNo) {
        return repairMapper.selectRepairState(repairStateNo);
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

    @Override
    public RepairApplyVO getRepairApply(Integer repairApplyNo) throws Exception {

        return repairMapper.selectRepairApply(repairApplyNo);
    }

    @Override
    public List<RepairApplyVO> getRepairApplyList(SelectRepairApplyDTO selectRepairApplyDTO) throws Exception {

        return repairMapper.selectRepairApplyList(selectRepairApplyDTO);
    }
}
