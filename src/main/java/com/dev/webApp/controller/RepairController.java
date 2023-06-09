package com.dev.webApp.controller;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.RepairApplyVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;
import com.dev.webApp.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@RestController
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairController extends BaseConfigController {

    private final RepairService repairService;

    // 수리 종류 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/type/list", produces = JSON_FORMAT)
    public ResponseEntity<List<RepairTypeVO>> getRepairTypeList() throws Exception {

        SelectRepairTypeDTO selectRepairTypeDTO = SelectRepairTypeDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairTypeVO> repairTypeVOList = repairService.getRepairTypeList(selectRepairTypeDTO);

        return ResponseEntity.ok(repairTypeVOList);
    }

    // 수리 지역 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/location/list", produces = JSON_FORMAT)
    public ResponseEntity<List<RepairLocationVO>> getRepairLocationList() throws Exception {

        SelectRepairLocationDTO selectRepairLocationDTO = SelectRepairLocationDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairLocationVO> repairLocationVOList = repairService.getRepairLocationList(selectRepairLocationDTO);

        return ResponseEntity.ok(repairLocationVOList);
    }

    // 수리 상태 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/state/list", produces = JSON_FORMAT)
    public ResponseEntity<List<RepairStateVO>> getRepairStateList() throws Exception {

        SelectRepairStateDTO selectRepairStateDTO = SelectRepairStateDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairStateVO> repairStateVOList = repairService.getRepairStateList(selectRepairStateDTO);

        return ResponseEntity.ok(repairStateVOList);
    }

    // 수리 신청 현황 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/list", produces = JSON_FORMAT)
    public ResponseEntity<List<RepairApplyVO>> getRepairApplyList(
            @RequestParam(required = false, defaultValue = "1")
                    Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
                    Integer pageSize
    ) throws Exception {

        if (StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO = SelectRepairApplyPaginationDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        List<RepairApplyVO> repairApplyVOList = repairService
                .getRepairApplyList(selectRepairApplyPaginationDTO)
                .getRepairApplyVOList();

        return ResponseEntity.ok(repairApplyVOList);
    }

    // client에서 수리 신청 현황을 당장 볼 일이 없으므로, 수리 신청 현황의 전체 개수를 불러오지는 않아도 될 것 같습니다.

    // 단일 수리 신청 현황만 json형식으로 불러오는 api
    @GetMapping(value = "", produces = JSON_FORMAT)
    public ResponseEntity<RepairApplyVO> getRepairApply(
            @RequestParam
                    Integer repairApplyNo
    ) throws Exception {

        RepairApplyVO repairApplyVO = repairService.getRepairApply(repairApplyNo);

        return ResponseEntity.ok(repairApplyVO);
    }

    // 단일 수리 신청 현황만 삽입하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PostMapping(value = "", produces = JSON_FORMAT)
    public ResponseEntity<Integer> insertRepairApply(
            @RequestBody
                    InsertRepairApplyDTO insertRepairApplyDTO
    ) throws Exception {

        Integer insertedRepairApplyNo = repairService.registerRepairApply(insertRepairApplyDTO);

        return ResponseEntity.ok(insertedRepairApplyNo);
    }

    // 단일 수리 신청 현황만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    // (이곳에서는 단일 수리 신청 현황 '상태'만 수정하는 api는 필요없다고 판단되어 넣지 않았습니다.)
    @PutMapping(value = "", produces = JSON_FORMAT)
    public ResponseEntity updateRepairApply(
            @RequestBody
                    UpdateRepairApplyDTO updateRepairApplyDTO
    ) throws Exception {

        repairService.modifyRepairApply(updateRepairApplyDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 단일 수리 신청 현황만 삭제하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @DeleteMapping(value = "", produces = JSON_FORMAT)
    public ResponseEntity deleteRepairApply(
            @RequestBody
                    Integer repairApplyNo
    ) throws Exception {

        repairService.removeRepairApply(repairApplyNo);

        return new ResponseEntity(HttpStatus.OK);
    }
}
