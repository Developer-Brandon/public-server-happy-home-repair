package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.InsertNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private NoticeService noticeService;

    @GetMapping("/content/list")
    public void selectNoticeList(Model model) {

        // 우선은 1000개로 지정하겠습니다.

        InsertNoticeDTO insertNoticeDTO = InsertNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .noticeSize(1000)
                .build();

        model.addAttribute("list", noticeService.getNoticeList(insertNoticeDTO));
    }

    @GetMapping("/content")
    public void getContent(
            @RequestParam
            Long noticeNo
            , Model model
    ) {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(noticeNo)
                .build();

        model.addAttribute("board", noticeService.getNotice(noticeVO));
    }

    @PostMapping("/content")
    public String postNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.registerNotice(noticeVO);

        redirectAttributes.addFlashAttribute("result", noticeVO.getNoticeNo());

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        return "redirect:/post/list";
    }

    @PutMapping("/content")
    public String putNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.modifyNotice(noticeVO);

        return "redirect:/notice/list";
    }

    @DeleteMapping("/content")
    public String deleteNotice(Long noticeNo, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.removeNotice(noticeNo);

        return "redirect:/notice/list";
    }
}
