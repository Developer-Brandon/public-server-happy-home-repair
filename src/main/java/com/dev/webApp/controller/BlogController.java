package com.dev.webApp.controller;

import com.dev.webApp.domain.vo.BlogPostingVO;
import com.dev.webApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    // todo: 아래 api의 경우 데이터만 주고 받는 식이니까, jsp를 return 하는 api도 만들기
    @GetMapping("/list/{type}")
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
            blogService.setOnlyDifferentBlogList();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 2. 데이터 베이스에 있는 포스팅 정보 리스트 불러오는 api
    // 모든 데이터를 긁어오도록 개발하여 클라이언트단에서 메인으로 뿌려주는 것으로 처리
    // (애초에 naver blog에서 불러오는 수가 정해져 있으므로, 그냥 사용해도 무관할 듯. 그리고 점점 수가 쌓여가는건 client에서 제한해서 사용해도 될 듯 )
    @GetMapping("/list")
    public ResponseEntity<List<BlogPostingVO>> getBlogPostingList() throws Exception {

        List<BlogPostingVO> allBlogList = blogService.getAllBlogList();

        return new ResponseEntity<>(allBlogList, HttpStatus.OK);
    }

    // 3. 단일 조회할 일이 없어서, 추후 필요한 일이 생기면 만드는 것으로.....
}
