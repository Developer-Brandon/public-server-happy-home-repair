package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    @ResponseBody
    public ResponseEntity<List<RepairApplyVO>> getRepairApplyList(
            @RequestParam(required = false)
            Integer itemSize
    ) throws Exception {

        SelectRepairApplyDTO selectRepairApplyDTO = SelectRepairApplyDTO.builder()
                .itemCnt(itemSize)
                .build();

        List<RepairApplyVO> repairApplyVOList = repairService.getRepairApplyList(selectRepairApplyDTO);

        return new ResponseEntity<>(repairApplyVOList, HttpStatus.OK);
    }

    @GetMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    @ResponseBody
    public ResponseEntity<RepairApplyVO> getRepairApply(
            @RequestParam
            Integer repairApplyNo
    ) throws Exception {

        RepairApplyVO repairApplyVO = repairService.getRepairApply(repairApplyNo);

        return new ResponseEntity<>(repairApplyVO, HttpStatus.OK);
    }

    @PostMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    @ResponseBody
    public ResponseEntity<Integer> insertRepairApply(
            @RequestBody
            InsertRepairApplyDTO insertRepairApplyDTO
    ) throws Exception{

        Integer insertedRepairApplyNo = repairService.registerRepairApply(insertRepairApplyDTO);

        return new ResponseEntity<>(insertedRepairApplyNo, HttpStatus.OK);
    }

    @PutMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    @ResponseBody
    public ResponseEntity updateRepairApply(
            @RequestBody
                    UpdateRepairApplyDTO updateRepairApplyDTO
    ) throws Exception{

        repairService.modifyRepairApply(updateRepairApplyDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(
            value = ""
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    @ResponseBody
    public ResponseEntity deleteRepairApply(
            @RequestBody
            Integer repairApplyNo
    ) throws Exception{

        repairService.removeRepairApply(repairApplyNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/apply/index")
    public String goApplyIndexPage() {
        return "/apply/index";
    }
}
