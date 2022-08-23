package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.InsertNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/notice")
@Controller // JSP로 이동하려면 json을 반환하는 RestController가 아닌,
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/content/list")
    public String selectNoticeList(Model model) {

        // 우선은 1000개로 지정하겠습니다.
        InsertNoticeDTO insertNoticeDTO = InsertNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .noticeSize(10000)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(insertNoticeDTO);
        model.addAttribute("list", noticeVOList);

        return "/notice/list_page";
    }

    @GetMapping("/content")
    public String getContent(
            @RequestParam("noticeNo")
            String noticeNo
            , Model model
    ) {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Long.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "/notice/page";
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
