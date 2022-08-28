package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectNoticeDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.ws.Response;
import java.util.List;

@RequestMapping("/notice")
@Controller // JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/content/list")
    public String getNoticeListAndView(Model model) throws Exception {

        // 우선은 1000개로 지정하겠습니다.
        SelectNoticeDTO selectNoticeDTO = SelectNoticeDTO.builder()
                .manyNoticeOrNot(false)
                .noticeSize(10000)
                .build();

        List<NoticeVO> noticeVOList = noticeService.getNoticeList(selectNoticeDTO);
        model.addAttribute("list", noticeVOList);

        return "/notice/get_list_page";
    }

    @GetMapping("/content")
    public String getNoticeAndView(
            @RequestParam
            String noticeNo
            , Model model
    ) throws Exception {

        NoticeVO noticeVO = NoticeVO.builder()
                .noticeNo(Long.valueOf(noticeNo))
                .build();

        model.addAttribute("notice", noticeService.getNotice(noticeVO));

        return "get_page";
    }

    @PostMapping("/content")
    public String postNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.registerNotice(noticeVO);

        redirectAttributes.addFlashAttribute("result", noticeVO.getNoticeNo());

        // 내부적으로 response.sendRedirect를 처리해주게끔 처리합니다.
        return "redirect:/notice/get_list_page";
    }

    @PutMapping("/content")
    public String putNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.modifyNotice(noticeVO);

        return "redirect:/notice/get_list_page";
    }

    @DeleteMapping("/content")
    public String deleteNotice(Long noticeNo, RedirectAttributes redirectAttributes) throws Exception {

        noticeService.removeNotice(noticeNo);

        return "redirect:/notice/get_list_page";
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
