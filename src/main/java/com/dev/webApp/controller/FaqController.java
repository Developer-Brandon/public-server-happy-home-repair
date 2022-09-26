package com.dev.webApp.controller;

import com.dev.webApp.config.BaseConfigController;
import com.dev.webApp.domain.dto.SelectFaqPaginationDTO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController extends BaseConfigController {

    private final FaqService faqService;

    // 자주하는질문 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/list")
    public ResponseEntity<List<FaqVO>> getFaqList(
            @RequestParam(required = false)
            Integer currentPage,
            @RequestParam(required = false)
            Integer pageSize
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectFaqPaginationDTO selectFaqPaginationDTO = SelectFaqPaginationDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        List<FaqVO> faqVOList = faqService
                .getFaqPaginationList(selectFaqPaginationDTO)
                .getFaqVOList();

        return new ResponseEntity(faqVOList, HttpStatus.OK);
    }

    // 자주하는질문의 개수를 불러오는 api
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> getWholeFaqCount() throws Exception {

        Integer wholeFaqCount = faqService.getTotalCnt();

        return new ResponseEntity<>(wholeFaqCount, HttpStatus.OK);
    }

    // 단일 자주하는질문만 json형식으로 불러오는 api
    @GetMapping(value="")
    public ResponseEntity<FaqVO> getFaq(
            @RequestParam
            Integer faqNo
    ) throws Exception {

        FaqVO requestFaqVO = FaqVO.builder()
                .faqNo(faqNo)
                .build();

        FaqVO faqVO = faqService.getFaq(requestFaqVO);

        return new ResponseEntity(faqVO, HttpStatus.OK);
    }

    // 단일 자주하는질문만 삽입하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PostMapping(value="")
    public ResponseEntity<Integer> insertFaq(
            @RequestBody
            FaqVO faqVO
    ) throws Exception {

        Integer faqNo = faqService.registerFaq(faqVO);

        return new ResponseEntity(faqNo, HttpStatus.OK);
    }

    // 단일 자주하는질문만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PutMapping(value="")
    public ResponseEntity updateFaq(
            @RequestBody
            FaqVO faqVO
    ) throws Exception {

        faqService.modifyFaq(faqVO);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 단일 자주하는질문만 상태만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PutMapping(value="/state")
    public ResponseEntity updateFaqState(
            @RequestBody
            FaqVO faqVO
    ) throws Exception {

        faqService.modifyFaqState(faqVO);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 단일 자주하는질문만 삭제하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @DeleteMapping(value="")
    public ResponseEntity deleteFaq(
            @RequestParam
            Integer faqNo
    ) throws Exception {

        faqService.removeFaq(faqNo);

        return new ResponseEntity(HttpStatus.OK);
    }
}
