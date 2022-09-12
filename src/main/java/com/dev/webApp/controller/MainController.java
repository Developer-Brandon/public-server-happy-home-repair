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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final RepairService repairService;

    @ResponseBody
    @GetMapping(
            value = "/test"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> test() {

        String dummyMessage = "안녕하세요";

        return new ResponseEntity<>(dummyMessage, HttpStatus.OK);
    }

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

    /////////////////////////////////////////////////////////

    @GetMapping("/index")
    public String goIndexPage(Model model) throws Exception {

        //
        SelectRepairTypeDTO selectRepairTypeDTO = SelectRepairTypeDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairTypeVO> repairTypeVOList = repairService.getRepairTypeList(selectRepairTypeDTO);

        model.addAttribute("repairTypeList", repairTypeVOList);

        //
        SelectRepairLocationDTO selectRepairLocationDTO = SelectRepairLocationDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairLocationVO> repairLocationVOList = repairService.getRepairLocationList(selectRepairLocationDTO);

        model.addAttribute("repairLocationList", repairLocationVOList);

        //
        SelectRepairStateDTO selectRepairStateDTO = SelectRepairStateDTO.builder()
                .itemCnt(0)
                .build();

        List<RepairStateVO> repairStateVOList = repairService.getRepairStateList(selectRepairStateDTO);

        model.addAttribute("repairStateList", repairStateVOList);

        //
        SelectRepairApplyDTO selectRepairApplyDTO = SelectRepairApplyDTO.builder()
                .itemCnt(1000)
                .build();

        List<RepairApplyVO> repairApplyVOList = repairService.getRepairApplyList(selectRepairApplyDTO);


        model.addAttribute("repairApplyList", repairApplyVOList);

        return "/main/index";
    }
}
