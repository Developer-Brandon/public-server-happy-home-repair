package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@Controller
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    private Model addRepairItemsToModel(Model model) {

        List<RepairTypeVO> repairTypeVOList = repairService.getRepairTypeList(SelectRepairTypeDTO.builder().build());

        model.addAttribute("repairTypeList", repairTypeVOList);

        List<RepairLocationVO> repairLocationVOList = repairService.getRepairLocationList(SelectRepairLocationDTO.builder().build());

        model.addAttribute("repairLocationList", repairLocationVOList);

        List<RepairStateVO> repairStateVOList = repairService.getRepairStateList(SelectRepairStateDTO.builder().build());

        model.addAttribute("repairStateList", repairStateVOList);

        return model;
    }

    // 수리 신청 현황 메인 페이지로 이동하는 api
    @GetMapping("/index")
    public String goIndexPage(
            @RequestParam(required = false)
            Integer currentPage
            , Model model
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        addRepairItemsToModel(model);

        SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO = SelectRepairApplyPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationRepairApplyVO repairApplyVO = repairService.getRepairApplyList(selectRepairApplyPaginationDTO);

        model.addAttribute("repairApplyList", repairApplyVO.getRepairApplyVOList());

        model.addAttribute("pageHandler", repairApplyVO.getPageHandler());

        return "/repair/index";
    }

    // 수리 신청 현황 페이지 진입 후 추가로 pagination 불러오는 api
    @GetMapping("/content/list")
    public String getRepairApplyListAtPage(
            @RequestParam
                Integer currentPage
            , Model model
    ) throws Exception {

        addRepairItemsToModel(model);

        SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO = SelectRepairApplyPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationRepairApplyVO repairApplyVO = repairService.getRepairApplyList(selectRepairApplyPaginationDTO);

        model.addAttribute("repairApplyList", repairApplyVO.getRepairApplyVOList());

        model.addAttribute("pageHandler", repairApplyVO.getPageHandler());

        return "/repair/index";
    }

    //////////////////////////////////////////////////////////////

    @ResponseBody
    @GetMapping(
            value = "/repair-type/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<RepairTypeVO>> getRepairTypeList() {

        SelectRepairTypeDTO selectRepairTypeDTO = SelectRepairTypeDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairTypeVO> repairTypeVOList = repairService.getRepairTypeList(selectRepairTypeDTO);

        return new ResponseEntity<>(repairTypeVOList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(
            value = "/repair-location/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<RepairLocationVO>> getRepairLocationList() {

        SelectRepairLocationDTO selectRepairLocationDTO = SelectRepairLocationDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairLocationVO> repairLocationVOList = repairService.getRepairLocationList(selectRepairLocationDTO);

        return new ResponseEntity<>(repairLocationVOList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(
            value = "/repair-state/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<RepairStateVO>> getRepairStateList() {

        SelectRepairStateDTO selectRepairStateDTO = SelectRepairStateDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairStateVO> repairStateVOList = repairService.getRepairStateList(selectRepairStateDTO);

        return new ResponseEntity<>(repairStateVOList, HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////

    // 수리 신청 현황 리스트만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<RepairApplyVO>> getRepairApplyList(
            @RequestParam(required = false)
            Integer currentPage
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectRepairApplyPaginationDTO selectRepairApplyPaginationDTO = SelectRepairApplyPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        List<RepairApplyVO> repairApplyVOList = repairService
                .getRepairApplyList(selectRepairApplyPaginationDTO)
                .getRepairApplyVOList();

        return new ResponseEntity<>(repairApplyVOList, HttpStatus.OK);
    }

    // 단일 수리 신청 현황만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<RepairApplyVO> getRepairApply(
            @RequestParam
            Integer repairApplyNo
    ) throws Exception {

        RepairApplyVO repairApplyVO = repairService.getRepairApply(repairApplyNo);

        return new ResponseEntity<>(repairApplyVO, HttpStatus.OK);
    }

    // 단일 수리 신청 현황만 삽입하는 api
    @ResponseBody
    @PostMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<Integer> insertRepairApply(
            @RequestBody
            InsertRepairApplyDTO insertRepairApplyDTO
    ) throws Exception{

        Integer insertedRepairApplyNo = repairService.registerRepairApply(insertRepairApplyDTO);

        return new ResponseEntity<>(insertedRepairApplyNo, HttpStatus.OK);
    }

    // 단일 수리 신청 현황만 수정하는 api
    @ResponseBody
    @PutMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity updateRepairApply(
            @RequestBody
                    UpdateRepairApplyDTO updateRepairApplyDTO
    ) throws Exception{

        repairService.modifyRepairApply(updateRepairApplyDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 이곳에서는 단일 수리 신청 현황만 수정하는 api는 필요없다고 판단되어 넣지 않았습니다.

    // 단일 수리 신청 현황만 삭제하는 api
    @ResponseBody
    @DeleteMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity deleteRepairApply(
            @RequestBody
            Integer repairApplyNo
    ) throws Exception{

        repairService.removeRepairApply(repairApplyNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
