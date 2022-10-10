package com.dev.webApp.controller;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.SelectNoticePaginationDTO;
import com.dev.webApp.domain.vo.NoticeVO;
import com.dev.webApp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// JSP로 이동하려면 json을 반환하는 RestController가 아닌, 그냥 controller를 사용해야 합니다
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController extends BaseConfigController {

    private final NoticeService noticeService;

    // 공지사항 리스트만 json형식으로 불러오는 api
    @GetMapping(value = "/list")
    public ResponseEntity<List<NoticeVO>> getNoticeList(
            @RequestParam(required = false, defaultValue = "1")
            Integer currentPage
            , @RequestParam(required = false, defaultValue = "10")
            Integer pageSize
    ) throws Exception {

        SelectNoticePaginationDTO selectNoticePaginationDTO = SelectNoticePaginationDTO.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();

        List<NoticeVO> noticeVOList = noticeService
                .getNoticePaginationList(selectNoticePaginationDTO)
                .getNoticeVOList();

        return new ResponseEntity(noticeVOList, HttpStatus.OK);
    }

    // 공지사항의 개수를 불러오는 api
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> getWholeNoticeCount() throws Exception {

        Integer wholeNoticeCount = noticeService.getTotalCnt();

        return new ResponseEntity<>(wholeNoticeCount, HttpStatus.OK);
    }

    // 단일 공지사항만 json형식으로 불러오는 api
    @GetMapping(value = "")
    public ResponseEntity<NoticeVO> getNotice(
            @RequestParam
            Integer noticeNo
    ) throws Exception {

        NoticeVO requestNoticeVO = NoticeVO.builder()
                .noticeNo(noticeNo)
                .build();

        NoticeVO noticeVO = noticeService.getNotice(requestNoticeVO);

        return new ResponseEntity(noticeVO, HttpStatus.OK);
    }

    // 단일 공지사항만 삽입하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PostMapping(value = "")
    public ResponseEntity<Long> insertNotice(
            @RequestBody
            NoticeVO noticeVO
    ) throws Exception {

        Integer noticeNo = noticeService.registerNotice(noticeVO);

        return new ResponseEntity(noticeNo, HttpStatus.OK);
    }

    // 단일 공지사항만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PutMapping(value = "")
    public ResponseEntity updateNotice(
            @RequestBody
            NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNotice(noticeVO);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 단일 공지사항 상태만 수정하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @PutMapping(value = "/state")
    public ResponseEntity updateNoticeState(
            @RequestBody
                    NoticeVO noticeVO
    ) throws Exception {

        noticeService.modifyNoticeState(noticeVO);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 단일 공지사항만 삭제하는 api
    // (admin에서만 필요할법한 기능이지만 혹시 몰라서 추가 개발)
    @DeleteMapping(value = "")
    public ResponseEntity deleteNotice(
            @RequestParam
                    Integer noticeNo
    ) throws Exception {

        noticeService.removeNotice(noticeNo);

        return new ResponseEntity(HttpStatus.OK);
    }
}
