package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.domain.vo.PaginationNoticeVO;
import com.dev.webApp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/notice")
@Controller // JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 공지사항 리스트 페이지로 이동하는 api
    @GetMapping("/index")
    public String goNoticeListPage(
            @RequestParam(required = false)
            Integer currentPage
            , Model model
    ) throws Exception {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationNoticeVO paginationNoticeVO = noticeService.getNoticePaginationList(selectNoticePaginationDTO);

        model.addAttribute("noticeList", paginationNoticeVO.getNoticeVOList());

        model.addAttribute("pageHandler", paginationNoticeVO.getPageHandler());

        return "/notice/index";
    }

    @GetMapping("/content/list")
    public String goNoticePageList(
            @RequestParam
            Integer currentPage
            , Model model
    ) throws Exception {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationNoticeVO paginationNoticeVO = noticeService.getNoticePaginationList(selectNoticePaginationDTO);

        model.addAttribute("noticeList", paginationNoticeVO.getNoticeVOList());

        model.addAttribute("pageHandler", paginationNoticeVO.getPageHandler());

        return "/notice/index";
    }

    // 공지사항 등록하기 페이지로 이동하는 api
    @GetMapping("/register")
    public String goNoticeRegisterPage(Model model) throws Exception {

        return "/notice/register";
    }

    // 공지사항 조회 페이지로 이동하는 api
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

        // 삽입한 번호를 반환하는 로직

        noticeService.registerNotice(noticeVO);
        //
        //        redirectAttributes.addFlashAttribute("result", noticeVO.getNoticeNo());

        // 전체 리스트를 조회해서 attribute로 설정해주는 로직
        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(true)
                .noticeSize(1000)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);

        redirectAttributes.addFlashAttribute("noticeList", noticeVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
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

        // 전체 리스트를 조회해서 attribute로 설정해주는 로직
        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(true)
                .noticeSize(1000)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);

        redirectAttributes.addFlashAttribute("noticeList", noticeVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        return "redirect:/notice/index";
    }

    // 공지사항 단일 삭제 후 리스트로 이동하는 api
    @PostMapping("/remove")
    public String goNoticeListPageAfterRemove(Long noticeNo, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.removeNotice(noticeNo);

        // 전체 리스트를 조회해서 attribute로 설정해주는 로직
        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(true)
                .noticeSize(1000)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);

        redirectAttributes.addFlashAttribute("noticeList", noticeVOList);

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        // 데이터 등록 후 리스트 페이지로 이동하게끔 처리
        return "redirect:/notice/index";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    @ResponseBody
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<NoticeVO>> getNoticeList(
            @RequestParam
            Boolean manyNoticeOrNot
            , @RequestParam
            Integer noticeSize
    ) throws Exception {

        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(manyNoticeOrNot)
                .noticeSize(noticeSize)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);

        return new ResponseEntity<>(noticeVOList, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
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

    @ResponseBody
    @PostMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<Long> insertNotice(
          @RequestBody
          NoticeVO noticeVO
    ) throws Exception {

        Long noticeNo = noticeService.registerNotice(noticeVO);

        return new ResponseEntity<>(noticeNo, HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity updateNotice(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNotice(noticeVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(
            value="/state"
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity updateNoticeState(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNoticeState(noticeVO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(
            value=""
            ,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity deleteNotice(
            @RequestParam
            Long noticeNo
    ) throws Exception {

        noticeService.removeNotice(noticeNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
