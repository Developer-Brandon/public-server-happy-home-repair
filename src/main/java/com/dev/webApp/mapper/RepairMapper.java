package com.dev.webApp.mapper;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.RepairApplyVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface RepairMapper {

    int getTotalCnt();

    List<RepairTypeVO> selectRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO);

    RepairTypeVO selectRepairType(@Param("repairTypeNo")Integer repairTypeNo);

    List<RepairLocationVO> selectRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO);

    RepairLocationVO selectRepairLocation(@Param("repairLocationNo")Integer repairLocationNo);

    List<RepairStateVO> selectRepairStateList(SelectRepairStateDTO selectRepairStateDTO);

    RepairStateVO selectRepairState(@Param("repairStateNo")Integer repairStateNo);

    int insertRepairApply(InsertRepairApplyDTO insertRepairApplyDTO);

    RepairApplyVO selectRepairApply(@Param("repairApplyNo")Integer repairApplyNo);

    List<RepairApplyVO> selectRepairApplyList(SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO);

    int updateRepairApply(UpdateRepairApplyDTO updateRepairApplyDTO);

    int deleteRepairApply(@Param("repairApplyNo")Integer repairApplyNo);
}
