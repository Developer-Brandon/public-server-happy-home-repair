package com.dev.webApp.controller;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.*;
import com.dev.webApp.domain.vo.*;
import com.dev.webApp.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@Controller
@RequiredArgsConstructor
@RequestMapping("/repair")
public class RepairViewController extends BaseConfigController {

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
    @GetMapping(value = "/index", produces = TEXT_HTML_FORMAT)
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
    @GetMapping(value = "/content/list", produces = TEXT_HTML_FORMAT)
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
}
