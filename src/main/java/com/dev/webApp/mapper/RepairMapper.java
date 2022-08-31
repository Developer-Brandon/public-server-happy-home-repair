package com.dev.webApp.mapper;

//    import com.dev.webApp.domain.dto.SelectRepairApplyDTO;
//    import com.dev.webApp.domain.vo.RepairApplyVO;

import com.dev.webApp.domain.dto.SelectRepairLocationDTO;
import com.dev.webApp.domain.dto.SelectRepairStateDTO;
import com.dev.webApp.domain.dto.SelectRepairTypeDTO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;

import java.util.List;

public interface RepairMapper {

    List<RepairTypeVO> selectRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO);

    RepairTypeVO selectRepairType(Integer repairTypeNo);

    List<RepairLocationVO> selectRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO);

    RepairLocationVO selectRepairLocation(Integer repairLocationNo);

    List<RepairStateVO> selectRepairStateList(SelectRepairStateDTO selectRepairStateDTO);

    RepairStateVO selectRepairState(Integer repairStateNo);

    //    List<RepairApplyVO> selectRepairApplyList(SelectRepairApplyDTO selectRepairApplyDTO);
    //
    //    RepairApplyVO selectRepairApply(Long repairApplyNo);
    //
    //    int insertRepairApply(RepairApplyVO repairApplyVO);
    //
    //    int insertRepairApplyList(List<RepairApplyVO> repairApplyVOList);
    //
    //    int updateRepairApply(RepairApplyVO repairApplyNo);
    //
    //    int updateRepairApplyState(RepairApplyVO repairApplyVO);
    //
    //    int deleteRepairApply(Long repairApplyNo);
}
