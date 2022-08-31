package com.dev.webApp.controller;

import com.dev.webApp.domain.vo.BlogVO;
import com.dev.webApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    // 1. 크롤링으로 데이터 베이스와 크롤링 데이터들을 비교해서 다르면 크롤링 데이터로 업데이트 api
    @GetMapping(
            value = "/list/checking"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> checkBlogPostingListStatus() throws Exception {

        blogService.setBlogList();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 2. 데이터 베이스에 있는 포스팅 정보 리스트 불러오는 api
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<BlogVO>> getRepairStateList() throws Exception {

        List<BlogVO> blogList = blogService.getBlogList();

        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
