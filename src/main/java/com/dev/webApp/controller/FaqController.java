package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.domain.vo.PaginationFaqVO;
import com.dev.webApp.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    private void addFaqListToModel(Model model, PaginationFaqVO paginationFaqVO) {

        model.addAttribute("faqList", paginationFaqVO.getFaqVOList());

        model.addAttribute("pageHandler", paginationFaqVO.getPageHandler());
    }

    // 자주하는질문 리스트 페이지로 이동하는 api
    @GetMapping("/index")
    public String goFaqListPage(
            @RequestParam(required = false)
                    Integer currentPage
            , Model model
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectFaqPaginationDTO selectNoticePaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationFaqVO paginationFaqVO = faqService.getFaqPaginationList(selectNoticePaginationDTO);

        addFaqListToModel(model, paginationFaqVO);

        return "/faq/index";
    }

    // 자주하는질문 페이지 진입 후 추가로 pagination 불러오는 api
    @GetMapping("/content/list")
    public String getFaqListAtPage(
            @RequestParam
                    Integer currentPage
            , Model model
    ) throws Exception {

        SelectFaqPaginationDTO selectNoticePaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationFaqVO paginationFaqVO = faqService.getFaqPaginationList(selectNoticePaginationDTO);

        addFaqListToModel(model, paginationFaqVO);

        return "/faq/index";
    }


    // 자주하는질문 등록하기 페이지로 이동하는 api
    @GetMapping("/register")
    public String goFaqRegisterPage(Model model) throws Exception {

        return "/faq/register";
    }

    // 자주하는질문 조회 페이지로 이동하는 api
    @GetMapping("/content")
    public String goFaqPage(
            @RequestParam
                    String faqNo
            , Model model
    ) throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .faqNo(Long.valueOf(faqNo))
                .build();

        model.addAttribute("faq", faqService.getFaq(faqVO));

        return "/faq/getter";
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

    // 공지사항 수정 페이지로 이동하는 api
    @GetMapping("/modifier")
    public String goFaqModifierPage(
            @RequestParam
            String faqNo
            , Model model
    ) throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .faqNo(Long.valueOf(faqNo))
                .build();

        model.addAttribute("faq", faqService.getFaq(faqVO));

        return "/faq/modifier";
    }


    @PostMapping("/modify")
    public String goFaqListPageAfterModify(FaqVO faqVO, RedirectAttributes redirectAttributes) throws Exception {

        faqService.modifyFaq(faqVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/faq/index";
    }

    @PostMapping("/remove")
    public String goFaqListPageAfterRemove(Long faqNo, RedirectAttributes redirectAttributes) throws Exception {

        faqService.removeFaq(faqNo);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/faq/index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    // 자주하는질문 리스트만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<FaqVO>> getFaqList(
            @RequestParam
                    Integer faqSize
    ) throws Exception {

        if(StringUtils.isEmpty(faqSize)) {
            faqSize = 10;
        }

        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .faqSize(faqSize)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        return new ResponseEntity<>(faqVOList, HttpStatus.OK);
    }

    // 단일 자주하는질문만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<FaqVO> getFaq(
            @RequestParam
                    Long faqNo
    ) throws Exception {

        FaqVO requestFaqVO = FaqVO.builder()
                .faqNo(faqNo)
                .build();

        FaqVO faqVO = faqService.getFaq(requestFaqVO);

        return new ResponseEntity<>(faqVO, HttpStatus.OK);
    }

    // 단일 자주하는질문만 삽입하는 api
    @ResponseBody
    @PostMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<Long> insertFaq(
            @RequestBody
            FaqVO faqVO
    ) throws Exception {

        Long faqNo = faqService.registerFaq(faqVO);

        return new ResponseEntity<>(faqNo, HttpStatus.OK);
    }

    // 단일 자주하는질문만 수정하는 api
    @ResponseBody
    @PutMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity updateFaq(
            @RequestBody
                    FaqVO faqVO
    ) throws Exception {

        faqService.modifyFaq(faqVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 단일 자주하는질문만 상태만 수정하는 api
    @ResponseBody
    @PutMapping(
            value="/state"
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity updateFaqState(
            @RequestBody
                    FaqVO faqVO
    ) throws Exception {

        faqService.modifyFaqState(faqVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 단일 자주하는질문만 삭제하는 api
    @ResponseBody
    @DeleteMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity deleteFaq(
            @RequestParam
                    Long faqNo
    ) throws Exception {

        faqService.removeFaq(faqNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
