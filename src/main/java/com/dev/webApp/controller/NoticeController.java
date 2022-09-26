package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    private Model addNoticeListToModel(Model model, PaginationNoticeVO paginationNoticeVO) {

        model.addAttribute("noticeList", paginationNoticeVO.getNoticeVOList());

        model.addAttribute("pageHandler", paginationNoticeVO.getPageHandler());

        return model;
    }

    // 공지사항 메인 페이지로 이동하는 api
    @GetMapping("/index")
    public String goNoticeIndexPage(
            @RequestParam(required = false)
            Integer currentPage
            , Model model
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationNoticeVO paginationNoticeVO = noticeService.getNoticePaginationList(selectNoticePaginationDTO);

        addNoticeListToModel(model, paginationNoticeVO);

        return "/notice/index";
    }

    // 공지사항 페이지 진입 후 추가로 pagination 불러오는 api
    @GetMapping("/content/list")
    public String getNoticeListAtPage(
            @RequestParam
            Integer currentPage
            , Model model
    ) throws Exception {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationNoticeVO paginationNoticeVO = noticeService.getNoticePaginationList(selectNoticePaginationDTO);

        addNoticeListToModel(model, paginationNoticeVO);

        return "/notice/index";
    }

    // 공지사항 등록하기 페이지로 이동하는 api
    @GetMapping("/register")
    public String goNoticeRegisterPage(Model model) throws Exception {

        return "/notice/register";
    }

    // 공지사항 상세 조회 페이지로 이동하는 api
    @GetMapping("/content")
    public String goNoticePage(
            @RequestParam
                    String noticeNo
            , Model model
    ) throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Long.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "/notice/getter";
    }

    // 공지사항 단일 등록 후 리스트로 이동하는 api
    @PostMapping("/content")
    public String postNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.registerNotice(noticeVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }

    // 공지사항 수정 페이지로 이동하는 api
    @GetMapping("/modifier")
    public String goNoticeModifierPage(
            @RequestParam
                    String noticeNo
            , Model model
    ) throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Long.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "/notice/modifier";
    }

    // 공지사항 단일 수정 후 리스트로 이동하는 api
    @PostMapping("/modify")
    public String goNoticeListPageAfterModify(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.modifyNotice(noticeVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }

    // 공지사항 단일 삭제 후 리스트로 이동하는 api
    @PostMapping("/remove")
    public String goNoticeListPageAfterRemove(Long noticeNo, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.removeNotice(noticeNo);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    // 공지사항 리스트만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value = "/list"
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<List<NoticeVO>> getNoticeList(
            @RequestParam(required = false)
            Integer currentPage,
            @RequestParam(required = false)
            Integer noticeSize
    ) throws Exception {

        if(StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        if(StringUtils.isEmpty(noticeSize)) {
            noticeSize = 10;
        }

        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .currentPage(currentPage)
                .noticeSize(noticeSize)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);

        return new ResponseEntity<>(noticeVOList, HttpStatus.OK);
    }

    // 공지사항의 개수를 불러오는 api
    @ResponseBody
    @GetMapping(
            value = "/count"
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<Integer> getNoticeList() throws Exception {

        Integer wholeNoticeCount = noticeService.getTotalCnt();

        return new ResponseEntity<>(wholeNoticeCount, HttpStatus.OK);
    }

    // 단일 공지사항만 json형식으로 불러오는 api
    @ResponseBody
    @GetMapping(
            value = ""
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<NoticeVO> getNotice(
            @RequestParam
                    Long noticeNo
    ) throws Exception {

        NoticeVO requestNoticeVO = NoticeVO.builder()
                .noticeNo(noticeNo)
                .build();

        NoticeVO noticeVO = noticeService.getNotice(requestNoticeVO);

        return new ResponseEntity<>(noticeVO, HttpStatus.OK);
    }

    // 단일 공지사항만 삽입하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @ResponseBody
    @PostMapping(
            value = ""
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<Long> insertNotice(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        Long noticeNo = noticeService.registerNotice(noticeVO);

        return new ResponseEntity<>(noticeNo, HttpStatus.OK);
    }

    // 단일 공지사항만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @ResponseBody
    @PutMapping(
            value = ""
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity updateNotice(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNotice(noticeVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 단일 공지사항 상태만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @ResponseBody
    @PutMapping(
            value = "/state"
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity updateNoticeState(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNoticeState(noticeVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 단일 공지사항만 삭제하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @ResponseBody
    @DeleteMapping(
            value = ""
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity deleteNotice(
            @RequestParam
                    Long noticeNo
    ) throws Exception {

        noticeService.removeNotice(noticeNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
