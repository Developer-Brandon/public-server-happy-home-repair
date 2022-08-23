package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.InsertNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private NoticeService noticeService;

    @GetMapping("/content/list")
    public String selectNoticeList(Model model) {

        // 우선은 1000개로 지정하겠습니다.
        InsertNoticeDTO insertNoticeDTO = InsertNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .noticeSize(1000)
                .build();

        // todo:
        // https://ecsimsw.tistory.com/entry/Spring-Controller%EC%97%90%EC%84%9C-View%EB%A1%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-%EC%A0%84%EB%8B%AC%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95

        // 위 링크 보고 다시...
        // List<NoticeVO> noticeVOList = noticeService.getNoticeList(insertNoticeDTO);
        model.addAttribute("list", "test-test");

        return "/notice/list";
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

        model.addAttribute("notice", noticeService.getNotice(noticeVO));
    }

    @PostMapping("/content")
    public String postNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.registerNotice(noticeVO);

        redirectAttributes.addFlashAttribute("result", noticeVO.getNoticeNo());

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        return "redirect:/notice/list";
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
