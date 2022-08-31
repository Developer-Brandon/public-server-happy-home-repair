package com.dev.webApp.service;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.dto.SelectRepairLocationDTO;
import com.dev.webApp.domain.dto.SelectRepairStateDTO;
import com.dev.webApp.domain.dto.SelectRepairTypeDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;

import java.util.List;

public interface RepairService {
    List<RepairTypeVO> getRepairTypeList(SelectRepairTypeDTO selectRepairTypeDTO);
    RepairTypeVO getRepairType(Integer repairTypeNo);
    List<RepairLocationVO> getRepairLocationList(SelectRepairLocationDTO selectRepairLocationDTO);
    RepairLocationVO getRepairLocation(Integer repairLocationNo);
    List<RepairStateVO> getRepairStateList(SelectRepairStateDTO selectRepairStateDTO);
    RepairStateVO getRepairState(Integer repairStateNo);

//    Long registerFaq(FaqVO FaqVO) throws Exception;
//
//    void registerFaqList(List<FaqVO> FaqVOList) throws Exception;
//
//    void modifyFaq(FaqVO FaqVO) throws Exception;
//
//    void modifyFaqState(FaqVO FaqVO) throws Exception;
//
//    void removeFaq(Long FaqNo) throws Exception;
//
//    FaqVO getFaq(FaqVO FaqVO) throws Exception;
//
//    List<FaqVO> getFaqList(SelectFaqDTO selectFaqDTO) throws Exception;
}
