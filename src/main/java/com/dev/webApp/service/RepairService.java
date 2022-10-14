package com.dev.webApp.service;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;

import java.util.List;
import java.util.Optional;

public interface RepairService {

    // 견적 문의 시 필요한 기본 정보들을 불러오는 api list
    List<RepairTypeVO> getRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO) throws Exception;
    RepairTypeVO getRepairType(Integer repairTypeNo) throws Exception;
    List<RepairLocationVO> getRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO) throws Exception;
    RepairLocationVO getRepairLocation(Integer repairLocationNo) throws Exception;
    List<RepairStateVO> getRepairStateList(SelectRepairStateDTO selectRepairStateDTO) throws Exception;
    RepairStateVO getRepairState(Integer repairStateNo) throws Exception;

    // 견적문의 관련 메소드 모음
    Integer registerRepairApply(InsertRepairApplyDTO insertRepairApplyDTO) throws Exception;
    // void registerRepairApplyList(List<RepairApplyVO> RepairApplyVOList) throws Exception;
    void modifyRepairApply(UpdateRepairApplyDTO updateRepairApplyDTO) throws Exception;
    // void modifyRepairApplyState(RepairApplyVO RepairApplyVO) throws Exception;
    void removeRepairApply(Integer repairApplyNo) throws Exception;
    RepairApplyVO getRepairApply(Integer repairApplyNo) throws Exception;
    PaginationRepairApplyVO getRepairApplyList(SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO) throws Exception;
}
