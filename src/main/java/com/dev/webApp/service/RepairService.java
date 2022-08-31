package com.dev.webApp.service;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.RepairApplyVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;

import java.util.List;

public interface RepairService {

    // 견적 문의 시 필요한 기본 정보들을 불러오는 api list
    List<RepairTypeVO> getRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO);
    RepairTypeVO getRepairType(Integer repairTypeNo);
    List<RepairLocationVO> getRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO);
    RepairLocationVO getRepairLocation(Integer repairLocationNo);
    List<RepairStateVO> getRepairStateList(SelectRepairStateDTO selectRepairStateDTO);
    RepairStateVO getRepairState(Integer repairStateNo);

    // 견적문의 관련 메소드 모음
    Integer registerRepairApply(InsertRepairApplyDTO insertRepairApplyDTO) throws Exception;
    // void registerRepairApplyList(List<RepairApplyVO> RepairApplyVOList) throws Exception;
    void modifyRepairApply(UpdateRepairApplyDTO updateRepairApplyDTO) throws Exception;
    // void modifyRepairApplyState(RepairApplyVO RepairApplyVO) throws Exception;
    void removeRepairApply(Integer repairApplyNo) throws Exception;
    RepairApplyVO getRepairApply(Integer repairApplyNo) throws Exception;
    List<RepairApplyVO> getRepairApplyList(SelectRepairApplyDTO selectRepairApplyDTO) throws Exception;
}
