package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    // 자주하는질문 리스트 페이지로 이동하는 api
    @GetMapping("/index")
    public String goFaqListPage(Model model) throws Exception {

        // 우선은 1000개로 지정하겠습니다.
        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);
        model.addAttribute("faqList", faqVOList);

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

        // 우선은 1000개로 지정하겠습니다.
        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        redirectAttributes.addFlashAttribute("faqList", faqVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
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

        // 우선은 1000개로 지정하겠습니다.
        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        redirectAttributes.addFlashAttribute("faqList", faqVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        return "redirect:/faq/index";
    }

    @PostMapping("/remove")
    public String goFaqListPageAfterRemove(Long faqNo, RedirectAttributes redirectAttributes) throws Exception {

        faqService.removeFaq(faqNo);

        // 우선은 1000개로 지정하겠습니다.
        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        redirectAttributes.addFlashAttribute("faqList", faqVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        return "redirect:/faq/index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    @ResponseBody
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<FaqVO>> getFaqList(
            @RequestParam
                    Boolean manyFaqOrNot
            , @RequestParam
                    Integer faqSize
    ) throws Exception {

        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(manyFaqOrNot)
                .faqSize(faqSize)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);

        return new ResponseEntity<>(faqVOList, HttpStatus.OK);
    }

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
