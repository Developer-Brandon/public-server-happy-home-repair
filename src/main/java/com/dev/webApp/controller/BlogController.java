package com.dev.webApp.controller;

import com.dev.webApp.domain.dto.SelectFaqDTO;
import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.domain.vo.FaqVO;
import com.dev.webApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    // 블로그 게시글 현황 리스트 페이지로 이동하는 api
    @GetMapping("/index")
    public String goBlogListPage(Model model) throws Exception {

        // 우선은 1000개로 지정하겠습니다.
        List<BlogPostingVO> blogList = blogService.getBlogList();

        model.addAttribute("blogList", blogList);

        return "/blog/index";
    }

    // todo: 단일 조회하는 api 만들기

    //////////////////////////////////////////////////////////////////////////////////////////////

    // todo: 아래 api의 경우 데이터만 주고 받는 식이니까, jsp를 return 하는 api도 만들기
    @GetMapping(
            value = "/list/{type}"
            , produces = "text/plain; charset=UTF-8"
    )
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<List<BlogPostingVO>> getBlogPostingList() throws Exception {

        List<BlogPostingVO> blogList = blogService.getBlogList();

        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
