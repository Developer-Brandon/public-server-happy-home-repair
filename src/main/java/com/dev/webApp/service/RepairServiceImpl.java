package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.dto.SelectRepairLocationDTO;
import com.dev.webApp.domain.dto.SelectRepairStateDTO;
import com.dev.webApp.domain.dto.SelectRepairTypeDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;
import com.dev.webApp.mapper.FaqMapper;
import com.dev.webApp.mapper.RepairMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
