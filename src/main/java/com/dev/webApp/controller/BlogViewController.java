package com.dev.webApp.controller;

import com.dev.webApp.config.controller.BaseConfigController;
import com.dev.webApp.domain.dto.SelectBlogPostingPaginationDTO;
import com.dev.webApp.domain.vo.PaginationBlogPostingVO;
import com.dev.webApp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogViewController extends BaseConfigController {

    private final BlogService blogService;

    // 블로그 게시글 현황 리스트 페이지로 이동하는 api
    @GetMapping("/index")
    public String goIndexPage(
            @RequestParam(required = false)
                    Integer currentPage
            , Model model
    ) throws Exception {

        if (StringUtils.isEmpty(currentPage)) {
            currentPage = 1;
        }

        SelectBlogPostingPaginationDTO selectBlogPostingPaginationDTO = SelectBlogPostingPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationBlogPostingVO paginationBlogPostingVO = blogService.getBlogList(selectBlogPostingPaginationDTO);

        model.addAttribute("blogList", paginationBlogPostingVO.getBlogPostingVOList());

        model.addAttribute("pageHandler", paginationBlogPostingVO.getPageHandler());

        return "/blog/index";
    }

    // 블로그 게시글 페이지 진입 후 추가로 paginnation 불러오는 api
    @GetMapping("/content/list")
    public String getBlogPostingListAtPage(
            @RequestParam
                    Integer currentPage
            , Model model
    ) throws Exception {

        SelectBlogPostingPaginationDTO selectBlogPostingPaginationDTO = SelectBlogPostingPaginationDTO.builder()
                .currentPage(currentPage)
                .build();

        PaginationBlogPostingVO paginationBlogPostingVO = blogService.getBlogList(selectBlogPostingPaginationDTO);

        model.addAttribute("blogList", paginationBlogPostingVO.getBlogPostingVOList());

        model.addAttribute("pageHandler", paginationBlogPostingVO.getPageHandler());

        return "/blog/index";
    }
}
