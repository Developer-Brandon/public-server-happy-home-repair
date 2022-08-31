package com.dev.webApp.controller;

import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    // 1.
    @GetMapping(
            value = "/list/${type}"
            , produces = "text/plain; charset=UTF-8"
    )
    public ResponseEntity<String> checkBlogPostingListStatus(
            @PathVariable
            String type
    ) throws Exception {

        if(type.equals("bulk")) {

            // 1-1. 크롤링한 데이터를 바로 insert하는 메소드
            // (최초 한번만 써야하는 api, 하지만 예외처리 되어있으니 덜 조심해도 될듯)

            blogService.setBlogList();

        } else if(type.equals("diff-bulk")){

            // 1-2. 크롤링으로 데이터 베이스와 크롤링 데이터들을 비교해서 다르면 크롤링 데이터로 업데이트하는 메소드
            blogService.setDifferentBlogList();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 2. 데이터 베이스에 있는 포스팅 정보 리스트 불러오는 api
    @GetMapping(
            value = "/list"
            , produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }
    )
    public ResponseEntity<List<BlogPostingVO>> getBlogPostingList() throws Exception {

        List<BlogPostingVO> blogList = blogService.getBlogList();

        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
