package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.dto.SelectRepairLocationDTO;
import com.dev.webApp.domain.dto.SelectRepairStateDTO;
import com.dev.webApp.domain.dto.SelectRepairTypeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.RepairLocationVO;
import com.dev.webApp.domain.vo.RepairStateVO;
import com.dev.webApp.domain.vo.RepairTypeVO;
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

    //        // 우선은 1000개로 지정하겠습니다.
    //        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
    //                .manyNoticeOrNot(false)
    //                .noticeSize(10000)
    //                .build();
    //
    //        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);
    //        model.addAttribute("list", noticeVOList);

        return "/main/index";
    }
}
