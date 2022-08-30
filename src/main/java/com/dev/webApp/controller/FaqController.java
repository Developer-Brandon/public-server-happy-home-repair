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

@RequestMapping("/faq")
@Controller
public class FaqController {

    @Autowired
    private FaqService faqService;

    @GetMapping("/content/list")
    public String getFaqListAndView(Model model) throws Exception {

        // 우선은 1000개로 지정하겠습니다.
        SelectFaqDTO selectFaqDTO = SelectFaqDTO.builder()
                .manyFaqOrNot(false)
                .faqSize(10000)
                .build();

        List<FaqVO> faqVOList = faqService.getFaqList(selectFaqDTO);
        model.addAttribute("list", faqVOList);

        return "/faq/get_list_page";
    }

    @GetMapping("/content")
    public String getFaqAndView(
            @RequestParam
                    String faqNo
            , Model model
    ) throws Exception {

        FaqVO faqVO = FaqVO.builder()
                .faqNo(Long.valueOf(faqNo))
                .build();

        model.addAttribute("faq", faqService.getFaq(faqVO));

        return "get_page";
    }

    @PostMapping("/content")
    public String postFaq(FaqVO faqVO, RedirectAttributes redirectAttributes) throws Exception {

        faqService.registerFaq(faqVO);

        redirectAttributes.addFlashAttribute("result", faqVO.getFaqNo());

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        return "redirect:/faq/get_list_page";
    }

    @PutMapping("/content")
    public String putFaq(FaqVO faqVO, RedirectAttributes redirectAttributes) throws Exception {

        faqService.modifyFaq(faqVO);

        return "redirect:/faq/get_list_page";
    }

    @DeleteMapping("/content")
    public String deleteFaq(Long faqNo, RedirectAttributes redirectAttributes) throws Exception {

        faqService.removeFaq(faqNo);

        return "redirect:/faq/get_list_page";
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
