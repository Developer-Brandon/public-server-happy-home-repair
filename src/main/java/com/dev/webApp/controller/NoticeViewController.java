package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeViewController extends BaseController{

    private final NoticeService noticeService;

    private Model addNoticeListToModel(Model model, PaginationNoticeVO paginationNoticeVO) {

        model.addAttribute("noticeList", paginationNoticeVO.getNoticeVOList());

        model.addAttribute("pageHandler", paginationNoticeVO.getPageHandler());

        return model;
    }

    // 공지사항 메인 페이지로 이동하는 api
    @GetMapping(value = "/index", produces = TEXT_HTML_FORMAT)
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
    @GetMapping(value = "/content/list", produces = TEXT_HTML_FORMAT)
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

    // 공지사항 상세 조회 페이지로 이동하는 api
    @GetMapping(value ="/content", produces = TEXT_HTML_FORMAT)
    public String goNoticePage(
            @RequestParam
                    String noticeNo
            , Model model
    ) throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Integer.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "/notice/getter";
    }

    // 공지사항 등록하기 페이지로 이동하는 api
    // 등록 페이지로 이동할때에는 아무 데이터를 넘기지 않아도 됩니다.
    @GetMapping(value = "/register", produces = TEXT_HTML_FORMAT)
    public String goNoticeRegisterPage(Model model) throws Exception {

        return "/notice/register";
    }

    // 공지사항 단일 등록 후 리스트로 이동하는 api
    @PostMapping(value = "/content", produces = TEXT_HTML_FORMAT)
    public String postNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.registerNotice(noticeVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }

    // 공지사항 수정 페이지로 이동하는 api
    @GetMapping(value = "/modifier", produces = TEXT_HTML_FORMAT)
    public String goNoticeModifierPage(
        @RequestParam
        String noticeNo
        , Model model
    ) throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Integer.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "/notice/modifier";
    }

    // 공지사항 단일 수정 후 리스트로 이동하는 api
    @PostMapping(value = "/modify", produces = TEXT_HTML_FORMAT)
    public String goNoticeListPageAfterModify(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.modifyNotice(noticeVO);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }

    // 공지사항 단일 삭제 후 리스트로 이동하는 api
    @PostMapping("/remove")
    public String goNoticeListPageAfterRemove(Integer noticeNo, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.removeNotice(noticeNo);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        // (현재는 그냥 첫번째 페이지로 이동하도록 설정합니다)
        return "redirect:/notice/index";
    }
}
