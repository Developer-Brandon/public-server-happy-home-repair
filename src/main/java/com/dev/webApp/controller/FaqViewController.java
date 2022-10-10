package com.dev.webApp.controller;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.PaginationFaqVO;
import com.dev.webApp.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqViewController extends BaseConfigController {

    private final FaqService faqService;

    private void addFaqListToModel(Model model, PaginationFaqVO paginationFaqVO) {

        model.addAttribute("faqList", paginationFaqVO.getFaqVOList());

        model.addAttribute("pageHandler", paginationFaqVO.getPageHandler());
    }

    // 자주하는질문 리스트 페이지로 이동하는 api
    @GetMapping(value ="/index", produces = TEXT_HTML_FORMAT)
    public String goFaqListPage(
            @RequestParam(required = false, defaultValue = "1")
            Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
            Integer pageSize
            , Model model
    ) throws Exception {

        SelectFaqPaginationDTO selectNoticePaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        PaginationFaqVO paginationFaqVO = faqService.getFaqPaginationList(selectNoticePaginationDTO);

        addFaqListToModel(model, paginationFaqVO);

        return "/faq/index";
    }

    // 자주하는질문 페이지 진입 후 추가로 pagination 불러오는 api
    @GetMapping(value = "/content/list", produces = TEXT_HTML_FORMAT)
    public String getFaqListAtPage(
            @RequestParam(defaultValue = "1")
                    Integer currentPage
            , @RequestParam(defaultValue = "10")
                    Integer pageSize
            , Model model
    ) throws Exception {

        SelectFaqPaginationDTO selectNoticePaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        PaginationFaqVO paginationFaqVO = faqService.getFaqPaginationList(selectNoticePaginationDTO);

        addFaqListToModel(model, paginationFaqVO);

        return "/faq/index";
    }

    // 자주하는질문 조회 페이지로 이동하는 api
    @GetMapping("/content")
    public String goFaqPage(
            @RequestParam
                    Integer faqNo
            , Model model
    ) throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .faqNo(faqNo)
                .build();

        model.addAttribute("faq", faqService.getFaq(faqVO));

        return "/faq/getter";
    }

    // 자주하는질문 등록하기 페이지로 이동하는 api
    // 등록 페이지로 이동할때에는 아무 데이터를 넘기지 않아도 됩니다.
    @GetMapping(value = "/register", produces = TEXT_HTML_FORMAT)
    public String goFaqRegisterPage(Model model) throws Exception {

        return "/faq/register";
    }

    // 자주하는질문 단일 등록 후 리스트로 이동하는 api
    @PostMapping("/content")
    public String postFaq(FaqVO faqVO, RedirectAttributes redirectAttributes) throws Exception {

        faqService.registerFaq(faqVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/faq/index";
    }

    // 자주하는질문 수정 페이지로 이동하는 api
    @GetMapping("/modifier")
    public String goFaqModifierPage(
            @RequestParam
            String faqNo
            , Model model
    ) throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .faqNo(Integer.valueOf(faqNo))
                .build();

        model.addAttribute("faq", faqService.getFaq(faqVO));

        return "/faq/modifier";
    }

    // 자주하는질문 단일 수정 후 리스트로 이동하는 api
    @PostMapping("/modify")
    public String goFaqListPageAfterModify(FaqVO faqVO, RedirectAttributes redirectAttributes) throws Exception {

        faqService.modifyFaq(faqVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/faq/index";
    }

    // 자주하는질문 단일 삭제 후 리스트로 이동하는 api
    @PostMapping("/remove")
    public String goFaqListPageAfterRemove(Integer faqNo, RedirectAttributes redirectAttributes) throws Exception {

        faqService.removeFaq(faqNo);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/faq/index";
    }
}
